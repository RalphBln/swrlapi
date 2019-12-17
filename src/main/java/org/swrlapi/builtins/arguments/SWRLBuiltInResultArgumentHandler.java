package org.swrlapi.builtins.arguments;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.swrlapi.exceptions.SWRLBuiltInException;
import org.swrlapi.literal.XSDDate;
import org.swrlapi.literal.XSDDateTime;
import org.swrlapi.literal.XSDDuration;
import org.swrlapi.literal.XSDTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This interface defines utility methods for built-in implementations to handle result processing.
 * <p>
 * Each method will take a list of built-in arguments, an index of a particular argument, and a generated result
 * argument of a particular type. It will determine if the specified argument and the generated result arguments are
 * equal, in which case it will evaluate to true; otherwise it will evaluate to false.
 *
 * @see org.swrlapi.builtins.AbstractSWRLBuiltInLibrary
 */
public interface SWRLBuiltInResultArgumentHandler
{
  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the byte result argument
   * @param generatedResultArgument The generated result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    byte generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated short result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    short generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated int result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    int generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated long result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    long generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated float result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    float generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated double result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    double generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated big decimal result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    BigDecimal generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated big integer result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    BigInteger generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated string result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    String generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated boolean result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    boolean generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated time result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull XSDTime generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated date result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull XSDDate generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated datetime result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull XSDDateTime generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated duration result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull XSDDuration generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments                A list of built-in arguments
   * @param resultArgumentNumber     The index of the result argument
   * @param generatedResultArguments The generated result arguments
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull Collection<@NonNull SWRLBuiltInArgument> generatedResultArguments) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull SWRLBuiltInArgument generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated OWL literal argument result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull SWRLLiteralBuiltInArgument generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments               A list of built-in arguments
   * @param resultArgumentNumber    The index of the result argument
   * @param generatedResultArgument The generated OWL literal result argument
   * @return If the specified argument is equal to the generated result argument
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultArgument(@NonNull List<@NonNull SWRLBuiltInArgument> arguments, int resultArgumentNumber,
    @NonNull OWLLiteral generatedResultArgument) throws SWRLBuiltInException;

  /**
   * @param arguments A list of built-in arguments
   * @return A map of built-in argument numbers to built-in arguments
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  @NonNull Map<@NonNull Integer, @NonNull SWRLMultiValueVariableBuiltInArgument> createOutputMultiValueArguments(
    @NonNull List<@NonNull SWRLBuiltInArgument> arguments) throws SWRLBuiltInException;

  /**
   * @param arguments                 A list of built-in arguments
   * @param outputMultiValueArguments A generated map of built-in argument numbers to built-in arguments
   * @return True if the predicate is satisfied
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  boolean processResultMultiValueArguments(@NonNull List<@NonNull SWRLBuiltInArgument> arguments,
    @NonNull Map<@NonNull Integer, @NonNull SWRLMultiValueVariableBuiltInArgument> outputMultiValueArguments)
    throws SWRLBuiltInException;

  /**
   *
   * @param value A double literal value
   * @param boundInputNumericArguments A list of numeric arguments
   * @return The literal with the least narrow type
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  @NonNull SWRLLiteralBuiltInArgument createLeastNarrowNumericLiteralBuiltInArgument(double value,
    @NonNull List<@NonNull SWRLBuiltInArgument> boundInputNumericArguments) throws SWRLBuiltInException;

  /**
   *
   * @param value A BigDecimal value
   * @param boundInputNumericArguments A list of numeric arguments
   * @return The literal with the least narrow type
   * @throws SWRLBuiltInException If an error occurs during processing
   */
  @NonNull SWRLLiteralBuiltInArgument createLeastNarrowNumericLiteralBuiltInArgument(
    @NonNull BigDecimal value, @NonNull List<@NonNull SWRLBuiltInArgument> boundInputNumericArguments)
    throws SWRLBuiltInException;

}
