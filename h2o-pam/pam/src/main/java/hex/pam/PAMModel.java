package hex.pam;

import hex.ModelCategory;
import hex.ModelMetrics;
import hex.ClusteringModel;
import hex.ModelMetricsClustering;
import water.H2O;
import water.Key;

import static hex.genmodel.GenModel.Kmeans_preprocessData;

public class PAMModel extends ClusteringModel<PAMModel,PAMModel.PAMParameters,PAMModel.PAMOutput> {

  public static class PAMParameters extends ClusteringModel.ClusteringParameters {
    public String algoName() { return "PAM"; }
    public String fullName() { return "Partitioning Around Medoids"; }
    public String javaName() { return PAMModel.class.getName(); }
    @Override public long progressUnits() { return _k; }
    public PAM.DissimilarityMeasure _dissimilarity_measure = PAM.DissimilarityMeasure.MANHATTAN; // for the dissimilarity measure, use either euclidean or manhattan distance
    public boolean _do_swap = true; // conduct SWAP phase
    public long[/*k*/] _initial_medoids; // row numbers of initial medoids to use instead of conducting BUILD phase
  }

  public static class PAMOutput extends ClusteringModel.ClusteringOutput {
    public int _swap_iterations; // number of swap iterations executed
    public long[/*k*/] _medoid_rows; // row numbers of medoids
    public double[/*k*/][/*p*/] _medoids; // the actual medoids
    //public double[/*k*/][/*p*/] _centers_raw;
    public double _sum_of_dissimilarities; // sum of dissimilarities of each observation to its medoid
    public int[/*iterations*/] _k = new int[]{0};
    public PAMOutput( PAM b ) { super(b); }
    @Override public ModelCategory getModelCategory() { return ModelCategory.Clustering; }
  }

  public PAMModel(Key selfKey, PAMParameters parms, PAMOutput output) { super(selfKey,parms,output); }

  @Override public ModelMetrics.MetricBuilder makeMetricBuilder(String[] domain) {
    assert domain == null;
    return new ModelMetricsClustering.MetricBuilderClustering(_output.nfeatures(), _parms._k);
  }

  @Override
  protected double[] score0(double[] data, double[] preds, double offset) {
    return score0(data, preds);
  }

  @Override protected double[] score0(double data[/*ncols*/], double preds[/*nclasses+1*/]) {
    double[][] centers = _output._medoids;
    preds[0] = hex.genmodel.GenModel.KMeans_closest(centers, data, _output._domains);
    return preds;
  }

}