package hex.schemas;

import hex.pam.PAMModel;
import water.api.API;
import water.api.schemas3.ModelOutputSchemaV3;
import water.api.schemas3.ModelSchemaV3;

public class PAMModelV99 extends ModelSchemaV3<PAMModel, PAMModelV99, PAMModel.PAMParameters, PAMV99.PAMParametersV99, PAMModel.PAMOutput, PAMModelV99.PAMModelOutputV99> {

  public static final class PAMModelOutputV99 extends ModelOutputSchemaV3<PAMModel.PAMOutput, PAMModelOutputV99> {
    @API(help="Cluster medoids") public double[/*k*/][/*p*/] medoids;
  }


  //==========================
  // Custom adapters go here

  // TOOD: I think we can implement the following two in ModelSchema, using reflection on the type parameters.
  public PAMV99.PAMParametersV99 createParametersSchema() { return new PAMV99.PAMParametersV99(); }
  public PAMModelOutputV99 createOutputSchema() { return new PAMModelOutputV99(); }


  // Version&Schema-specific filling into the impl
  @Override public PAMModel createImpl() {
    PAMModel.PAMParameters parms = parameters.createImpl();
    return new PAMModel( model_id.key(), parms, null );
  }
}