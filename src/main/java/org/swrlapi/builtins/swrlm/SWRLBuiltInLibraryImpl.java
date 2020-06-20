package org.swrlapi.builtins.swrlm;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.nfunk.jep.JEP;
import org.semanticweb.owlapi.vocab.XSDVocabulary;
import org.swrlapi.builtins.AbstractSWRLBuiltInLibrary;
import org.swrlapi.builtins.arguments.SWRLBuiltInArgument;
import org.swrlapi.exceptions.SWRLBuiltInException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Implementations library for SWRL mathematical built-ins
 */
public class SWRLBuiltInLibraryImpl extends AbstractSWRLBuiltInLibrary
{
  private static final String PREFIX = "swrlm";

  private static final String NAMESPACE = "http://swrl.stanford.edu/ontologies/built-ins/3.4/swrlm.owl#";

  private static final String[] BUILT_IN_NAMES = { "sqrt", "eval", "log" };

  private static final MathContext mathContext = new MathContext(100);

  @Nullable private JEP jep = null;

  public SWRLBuiltInLibraryImpl()
  {
    super(PREFIX, NAMESPACE, new HashSet<>(Arrays.asList(BUILT_IN_NAMES)));
  }

  @Override public void reset()
  {
    this.jep = null;
  }

  /**
   * Returns true if the first argument is equal to the square root of the second argument. If the first argument is
   * unbound, bind it to the square root of the second argument.
   *
   * @param arguments The built-in arguments
   * @return The result of the built-in
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  public boolean sqrt(@NonNull List<@NonNull SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {
    checkNumberOfArgumentsAtLeast(2, arguments.size());
    checkForUnboundNonFirstArguments(arguments);

    BigDecimal argument2 = getArgumentAsADecimal(1, arguments);
    BigDecimal operationResult = BigDecimalMath.sqrt(argument2, mathContext);

    if (isUnboundArgument(0, arguments)) {
      arguments.get(0).asVariable().setBuiltInResult(createLiteralBuiltInArgument(operationResult));
      return true;
    } else {
      BigDecimal argument1 = getArgumentAsADecimal(0, arguments);
      return argument1.equals(operationResult);
    }
  }

  /**
   * Returns true if the first argument is equal to the natural logarithm (base e) of the second argument. If the first
   * argument is unbound, bind it to the natural logarithm of the second argument.
   *
   * @param arguments The built-in arguments
   * @return The result of the built-in
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  public boolean log(@NonNull List<@NonNull SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {
    checkNumberOfArgumentsAtLeast(2, arguments.size());
    checkForUnboundNonFirstArguments(arguments);

    BigDecimal argument2 = getArgumentAsADecimal(1, arguments);
    BigDecimal operationResult = BigDecimalMath.log10(argument2, mathContext);

    if (isUnboundArgument(0, arguments)) {
      arguments.get(0).asVariable().setBuiltInResult(createLiteralBuiltInArgument(operationResult));
      return true;
    } else {
      BigDecimal argument1 = getArgumentAsADecimal(0, arguments);
      return argument1.equals(operationResult);
    }
  }

  /**
   * Returns true if the first argument is equals to the mathematical expression specified in the second argument, which
   * may use the values specified by the variables in the optional subsequent arguments. If the first argument is
   * unbound, bind it to the result of the expression.
   *
   * @param arguments The built-in arguments
   * @return The result of the built-in
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  public boolean eval(@NonNull List<@NonNull SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {
    final int minimumNumberoOfArguments = 2;
    final int expressionArgumentIndex = 1;
    final int resultArgumentIndex = 0;

    checkNumberOfArgumentsAtLeast(minimumNumberoOfArguments, arguments.size());
    checkForUnboundNonFirstArguments(arguments);

    String expression = getArgumentAsAString(expressionArgumentIndex, arguments);

    if (arguments.size() > minimumNumberoOfArguments) {
      List<@NonNull SWRLBuiltInArgument> variableArguments = arguments
        .subList(minimumNumberoOfArguments, arguments.size());

      checkForUnboundArguments(variableArguments, "2nd and subsequent arguments cannot be unbound");
      checkThatAllArgumentsAreBoundVariables(variableArguments, "2nd and subsequent arguments should be variables");

      int currentVariableArgumentIndex = minimumNumberoOfArguments;
      for (SWRLBuiltInArgument variableArgument : variableArguments) {
        Optional<String> variableName = variableArgument
          .getBoundVariableName(); // We have checked that they are all variables
        if (variableName.isPresent()) {
          if (isArgumentConvertibleToDouble(currentVariableArgumentIndex, arguments)) {
            double variableValue = getArgumentAsADouble(variableArgument);
            getJEP().addVariable(variableName.get(), variableValue);
          } else {
            String message =
              "exception processing expression '" + expression + "': " + "variable ?" + variableName.get()
                + " with type " + getLiteralArgumentDatatypeName(currentVariableArgumentIndex, arguments)
                + " cannot be converted to " + XSDVocabulary.DOUBLE.getPrefixedName();
            throw new SWRLBuiltInException(message);
          }
          currentVariableArgumentIndex++;
        }
      }
    }

    getJEP().parseExpression(expression);

    if (getJEP().hasError())
      throw new SWRLBuiltInException("exception parsing expression '" + expression + "': " + getJEP().getErrorInfo());

    double value = getJEP().getValue();

    if (getJEP().hasError())
      throw new SWRLBuiltInException("exception parsing expression '" + expression + "': " + getJEP().getErrorInfo());

    if (isUnboundArgument(resultArgumentIndex, arguments)) {
      arguments.get(resultArgumentIndex).asVariable().setBuiltInResult(createLiteralBuiltInArgument(value));
      return true;
    } else { // Explicit argument present - check that it can be converted to an xsd:double
      if (isArgumentConvertibleToDouble(resultArgumentIndex, arguments))
        return value == getArgumentAsADouble(resultArgumentIndex, arguments);
      else
        throw new SWRLBuiltInException(
          "exception processing expression '" + expression + "': " + "result argument with type "
            + getLiteralArgumentDatatypeName(resultArgumentIndex, arguments) + " cannot be converted to "
            + XSDVocabulary.DOUBLE.getPrefixedName());
    }
  }

  // See. http://www.singularsys.com/jep/doc/javadoc/org/nfunk/jep/JEP.html for JEP API
  @NonNull private JEP getJEP()
  {
    if (this.jep == null) {
      this.jep = new JEP();

      this.jep.addStandardFunctions();
      this.jep.addStandardConstants();
      this.jep.setImplicitMul(true);
    }
    return this.jep;
  }
}
