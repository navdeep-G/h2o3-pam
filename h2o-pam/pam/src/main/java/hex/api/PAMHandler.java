//TODO Need work
//package hex.api;
//
//import hex.pam.PAM;
//import hex.schemas.PAMV99;
//import water.H2O;
//import water.Job;
//import water.api.FramesHandler;
//import hex.Model;
//import water.api.schemas3.JobV3;
//import water.fvec.Frame;
//import water.api.Handler;
//import water.api.ModelsHandler;
//import water.Key;
//
//public class PAMHandler extends Handler {
//
//    public JobV3 getPAM(int version, final PAMV99.PAMParametersV99 args) {
//
//        final Frame frame = FramesHandler.getFromDKV("training_frame", args.training_frame.key());
//        final PAM.DissimilarityMeasure dissimilarity_measure = args.dissimilarity_measure;
//        final boolean do_swap = args.do_swap;
//        final int k = args.k;
//
//        final Job<Frame> job = new Job(Key.make("PAM"), Frame.class.getName(), "Conduct PAM Clustering");
//        H2O.H2OCountedCompleter work = new H2O.H2OCountedCompleter() {
//                @Override
//                public void compute2() {
//                    //Frame f, Frame ddreer, int k, double[][] medoids, long[] medoidRows, DissimilarityMeasure dissimilarityMeasure
//                    PAM.buildPhase();
//                    LeaveOneCovarOut.leaveOneCovarOut(model,frame,job,replaceVal,Key.make(dest_frame_id), leafRemovalMethod);
//                    tryComplete();
//                }
//            };
//
//        job.start(work, model._output._names.length);
//        return new JobV3().fillFromImpl(job);
//    }
//
//}
