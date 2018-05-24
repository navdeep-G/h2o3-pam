package hex.schemas;

import hex.pam.PAM;
import hex.pam.PAMModel;
import water.Iced;
import water.api.API;
import water.api.schemas3.ClusteringModelParametersSchemaV3;
import water.api.schemas3.KeyV3;
import water.api.schemas3.SchemaV3;

public class PAMV99 extends SchemaV3<Iced, PAMV99> {

  public static final class PAMParametersV99 extends ClusteringModelParametersSchemaV3<PAMModel.PAMParameters, PAMParametersV99> {
    static public String[] fields = new String[] {
            "training_frame",
            "ignored_columns",
            "dissimilarity_measure",
            "do_swap",
            "initial_medoids"
    };

    @API(help="training_frame",required = true, direction = API.Direction.INPUT)
    public KeyV3.FrameKeyV3 training_frame;

    @API(help="ignored_columns")
    public String[] ignored_columns;

    @API(help="dissimilarity_measure", values = {"MANHATTAN", "EUCLIDEAN" })
    public PAM.DissimilarityMeasure dissimilarity_measure;

    @API(help="do_swap")
    public boolean do_swap;

    @API(help="initial_medoids")
    public long[] initial_medoids;
  }
}