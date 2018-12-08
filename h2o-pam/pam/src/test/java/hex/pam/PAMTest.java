package hex.pam;

import org.junit.BeforeClass;
import org.junit.Test;
import water.*;
import water.fvec.FVecTest;
import water.fvec.Frame;
import water.parser.ParseDataset;
import static org.junit.Assert.assertTrue;

public class PAMTest extends TestUtil {
  @BeforeClass() public static void setup() { stall_till_cloudsize(1); }

  @Test public void testBUILDPhase1() {
    PAMModel kmm = null;
    Key raw = Key.make("1D_pam_data_raw");
    Key parsed = Key.make("1D_pam_data_parsed");
    Frame tf = null;
    try {
      FVecTest.makeByteVec(raw, "x\n0\n1\n2\n10\n11\n12\n20\n21\n22");
      tf = ParseDataset.parse(parsed, raw);

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = tf._key;
      parms._k = 3;
      parms._do_swap = false;

      kmm = new PAM(parms).trainModel().get();

      assertTrue(kmm._output._medoid_rows.length == 3);
      assertTrue(contains(kmm._output._medoid_rows,1)); // row 1 = 1
      assertTrue(contains(kmm._output._medoid_rows,4)); // row 4 = 11
      assertTrue(contains(kmm._output._medoid_rows,7)); // row 7 = 21
    } finally {
      if( tf  != null ) tf.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testBUILDPhase2() {
    PAMModel kmm = null;
    Key raw = Key.make("1D_pam_data_raw");
    Key parsed = Key.make("1D_pam_data_parsed");
    Frame tf = null;
    try {
      FVecTest.makeByteVec(raw, "x\n0\n1\n2\n51\n100\n101\n102");
      tf = ParseDataset.parse(parsed, raw);

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = tf._key;
      parms._k = 2;
      parms._do_swap = false;

      kmm = new PAM(parms).trainModel().get();

      assertTrue(kmm._output._medoid_rows.length == 2);
      assertTrue(contains(kmm._output._medoid_rows,3));
      assertTrue(contains(kmm._output._medoid_rows,5));
    } finally {
      if( tf  != null ) tf.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testBUILDPhase1Euclidean() {
    PAMModel kmm = null;
    Key raw = Key.make("1D_pam_data_raw");
    Key parsed = Key.make("1D_pam_data_parsed");
    Frame tf = null;
    try {
      FVecTest.makeByteVec(raw, "x\n0\n1\n2\n51\n100\n101\n102");
      tf = ParseDataset.parse(parsed, raw);

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = tf._key;
      parms._k = 2;
      parms._dissimilarity_measure = PAM.DissimilarityMeasure.EUCLIDEAN;
      parms._do_swap = false;

      kmm = new PAM(parms).trainModel().get();

      assertTrue(kmm._output._medoid_rows.length == 2);
      assertTrue(contains(kmm._output._medoid_rows,3));
      assertTrue(contains(kmm._output._medoid_rows,5));
    } finally {
      if( tf  != null ) tf.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testBUILDPhaseIris() {
    PAMModel kmm = null;
    Frame tf = null;
    try {
      tf = parse_test_file(Key.make("iris.hex"),"smalldata/iris/iris.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = tf._key;
      parms._k = 3;
      parms._do_swap = false;

      kmm = new PAM(parms).trainModel().get();

      assertTrue(kmm._output._medoid_rows.length == 3);
      assertTrue(contains(kmm._output._medoid_rows,7));
      assertTrue(contains(kmm._output._medoid_rows,95));
      assertTrue(contains(kmm._output._medoid_rows,116));

    } finally {
      if( tf  != null ) tf.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testBUILDPhaseProstate() {
    PAMModel kmm = null;
    Frame tf = null;
    try {
      tf = parse_test_file(Key.make("prostate.hex"),"smalldata/prostate/prostate.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = tf._key;
      parms._k = 8;
      parms._do_swap = false;

      kmm = new PAM(parms).trainModel().get();

      assertTrue(kmm._output._medoid_rows.length == 8);
      assertTrue(contains(kmm._output._medoid_rows,17));
      assertTrue(contains(kmm._output._medoid_rows,65));
      assertTrue(contains(kmm._output._medoid_rows,125));
      assertTrue(contains(kmm._output._medoid_rows,175));
      assertTrue(contains(kmm._output._medoid_rows,204));
      assertTrue(contains(kmm._output._medoid_rows,240));
      assertTrue(contains(kmm._output._medoid_rows,305));
      assertTrue(contains(kmm._output._medoid_rows,357));

    } finally {
      if( tf  != null ) tf.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testBUILDPhaseProstateEuclidean() {
    PAMModel kmm = null;
    Frame tf = null;
    try {
      tf = parse_test_file(Key.make("prostate.hex"),"smalldata/prostate/prostate.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = tf._key;
      parms._k = 8;
      parms._dissimilarity_measure = PAM.DissimilarityMeasure.EUCLIDEAN;
      parms._do_swap = false;

      kmm = new PAM(parms).trainModel().get();

      assertTrue(kmm._output._medoid_rows.length == 8);
      assertTrue(contains(kmm._output._medoid_rows,17));
      assertTrue(contains(kmm._output._medoid_rows,54));
      assertTrue(contains(kmm._output._medoid_rows,125));
      assertTrue(contains(kmm._output._medoid_rows,193));
      assertTrue(contains(kmm._output._medoid_rows,239));
      assertTrue(contains(kmm._output._medoid_rows,264));
      assertTrue(contains(kmm._output._medoid_rows,320));
      assertTrue(contains(kmm._output._medoid_rows,357));
    } finally {
      if( tf  != null ) tf.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void test1() {
    PAMModel kmm = null;
    Key raw = Key.make("1D_pam_data_raw");
    Key parsed = Key.make("1D_pam_data_parsed");
    Frame fr = null;
    try {
      FVecTest.makeByteVec(raw, "x\n1\n10\n100");
      fr = ParseDataset.parse(parsed, raw);

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = fr._key;
      parms._k = 1;

      kmm = new PAM(parms).trainModel().get();
      assertTrue(1==kmm._output._medoid_rows[0]);
      assertTrue(10==kmm._output._medoids[0][0]);

    } finally {
      if( fr  != null ) fr.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void test2() {
    PAMModel kmm = null;
    Key raw = Key.make("1D_pam_data_raw");
    Key parsed = Key.make("1D_pam_data_parsed");
    Frame fr = null;
    try {
      FVecTest.makeByteVec(raw, "x\n0\n1\n2\n10\n11\n12\n20\n21\n22");
      fr = ParseDataset.parse(parsed, raw);

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = fr._key;
      parms._k = 3;

      kmm = new PAM(parms).trainModel().get();
      assertTrue(contains(kmm._output._medoid_rows,4));
      assertTrue(contains(kmm._output._medoid_rows,1));
      assertTrue(contains(kmm._output._medoid_rows,7));
    } finally {
      if( fr  != null ) fr.delete();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testIris() {
    PAMModel kmm = null;
    Frame fr = null;
    try {
      fr = parse_test_file("smalldata/iris/iris_wheader.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = fr._key;
      parms._k = 4;

      Job<PAMModel> job = new PAM(parms).trainModel();
      kmm = job.get();
      assertTrue(contains(kmm._output._medoid_rows,7));
      assertTrue(contains(kmm._output._medoid_rows,91));
      assertTrue(contains(kmm._output._medoid_rows,69));
      assertTrue(contains(kmm._output._medoid_rows,112));

      job.remove();

    } finally {
      if( fr  != null ) fr .remove();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testProstate() {
    PAMModel kmm = null;
    Frame fr = null;
    try {
      fr = parse_test_file("smalldata/prostate/prostate.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = fr._key;
      parms._k = 5;

      Job<PAMModel> job = new PAM(parms).trainModel();
      kmm = job.get();
      assertTrue(contains(kmm._output._medoid_rows,49));
      assertTrue(contains(kmm._output._medoid_rows,132));
      assertTrue(contains(kmm._output._medoid_rows,214));
      assertTrue(contains(kmm._output._medoid_rows,282));
      assertTrue(contains(kmm._output._medoid_rows,357));

      job.remove();

    } finally {
      if( fr  != null ) fr .remove();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testProstateEuclidean() {
    PAMModel kmm = null;
    Frame fr = null;
    try {
      fr = parse_test_file("smalldata/prostate/prostate.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = fr._key;
      parms._k = 5;
      parms._dissimilarity_measure = PAM.DissimilarityMeasure.EUCLIDEAN;

      Job<PAMModel> job = new PAM(parms).trainModel();
      kmm = job.get();
      assertTrue(contains(kmm._output._medoid_rows,47));
      assertTrue(contains(kmm._output._medoid_rows,125));
      assertTrue(contains(kmm._output._medoid_rows,193));
      assertTrue(contains(kmm._output._medoid_rows,264));
      assertTrue(contains(kmm._output._medoid_rows,341));

      job.remove();

    } finally {
      if( fr  != null ) fr .remove();
      if( kmm != null ) kmm.delete();
    }
  }

  @Test public void testProstate2() {
    PAMModel kmm = null;
    Frame fr = null;
    try {
      fr = parse_test_file("smalldata/prostate/prostate.csv");

      PAMModel.PAMParameters parms = new PAMModel.PAMParameters();
      parms._train = fr._key;
      parms._k = 8;

      Job<PAMModel> job = new PAM(parms).trainModel();
      kmm = job.get();
      assertTrue(contains(kmm._output._medoid_rows,15));
      assertTrue(contains(kmm._output._medoid_rows,40));
      assertTrue(contains(kmm._output._medoid_rows,92));
      assertTrue(contains(kmm._output._medoid_rows,129));
      assertTrue(contains(kmm._output._medoid_rows,173));
      assertTrue(contains(kmm._output._medoid_rows,240));
      assertTrue(contains(kmm._output._medoid_rows,303));
      assertTrue(contains(kmm._output._medoid_rows,357));

      job.remove();

    } finally {
      if( fr  != null ) fr .remove();
      if( kmm != null ) kmm.delete();
    }
  }

  static boolean contains ( long[] la, long ll) {
    boolean in = false;
    for (long l : la) {
      if (l == ll) in = true;
    }
    return in;
  }
}