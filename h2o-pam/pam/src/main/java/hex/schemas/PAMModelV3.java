package hex.schemas;

import hex.pam.PAMModel;
import water.api.API;
import water.api.schemas3.ModelOutputSchemaV3;
import water.api.schemas3.ModelSchemaV3;

public class PAMModelV3 extends ModelSchemaV3<PAMModel, PAMModelV3, PAMModel.PAMParameters, PAMV3.PAMParametersV3, PAMModel.PAMOutput, PAMModelV3.PAMModelOutputV3> {

  public static final class PAMModelOutputV3 extends ModelOutputSchemaV3<PAMModel.PAMOutput, PAMModelOutputV3> {
    @API(help="Cluster medoids") public double[/*k*/][/*p*/] medoids;
  }

  public PAMV3.PAMParametersV3 createParametersSchema() { return new PAMV3.PAMParametersV3(); }
  public PAMModelOutputV3 createOutputSchema() { return new PAMModelOutputV3(); }


  // Version&Schema-specific filling into the impl
  @Override public PAMModel createImpl() {
    PAMModel.PAMParameters parms = parameters.createImpl();
    return new PAMModel( model_id.key(), parms, null );
  }
}