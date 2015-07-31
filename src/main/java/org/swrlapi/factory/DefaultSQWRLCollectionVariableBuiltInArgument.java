package org.swrlapi.factory;

import checkers.nullness.quals.NonNull;
import org.semanticweb.owlapi.model.IRI;
import org.swrlapi.builtins.arguments.SQWRLCollectionVariableBuiltInArgument;
import org.swrlapi.builtins.arguments.SWRLBuiltInArgumentVisitor;
import org.swrlapi.builtins.arguments.SWRLBuiltInArgumentVisitorEx;

class DefaultSQWRLCollectionVariableBuiltInArgument extends DefaultSWRLVariableBuiltInArgument
  implements SQWRLCollectionVariableBuiltInArgument
{
  private static final long serialVersionUID = 1L;

  @NonNull private final String queryName, collectionName, collectionGroupID;

  public DefaultSQWRLCollectionVariableBuiltInArgument(@NonNull IRI variableIRI, @NonNull String variablePrefixedName,
    @NonNull String queryName, @NonNull String collectionName, @NonNull String collectionGroupID)
  {
    super(variableIRI, variablePrefixedName);
    this.queryName = queryName;
    this.collectionName = collectionName;
    this.collectionGroupID = collectionGroupID;
  }

  @Override public String getGroupID()
  {
    return this.collectionGroupID;
  }

  @NonNull @Override public String getQueryName()
  {
    return this.queryName;
  }

  @NonNull @Override public String getCollectionName()
  {
    return this.collectionName;
  }

  @NonNull @Override public <T> T accept(@NonNull SWRLBuiltInArgumentVisitorEx<T> visitor)
  {
    return visitor.visit(this);
  }

  @Override public void accept(@NonNull SWRLBuiltInArgumentVisitor visitor)
  {
    visitor.visit(this);
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;

    DefaultSQWRLCollectionVariableBuiltInArgument that = (DefaultSQWRLCollectionVariableBuiltInArgument)o;

    if (queryName != null ? !queryName.equals(that.queryName) : that.queryName != null)
      return false;
    if (collectionName != null ? !collectionName.equals(that.collectionName) : that.collectionName != null)
      return false;
    return !(collectionGroupID != null ?
      !collectionGroupID.equals(that.collectionGroupID) :
      that.collectionGroupID != null);

  }

  @Override public int hashCode()
  {
    int result = super.hashCode();
    result = 31 * result + (queryName != null ? queryName.hashCode() : 0);
    result = 31 * result + (collectionName != null ? collectionName.hashCode() : 0);
    result = 31 * result + (collectionGroupID != null ? collectionGroupID.hashCode() : 0);
    return result;
  }
}
