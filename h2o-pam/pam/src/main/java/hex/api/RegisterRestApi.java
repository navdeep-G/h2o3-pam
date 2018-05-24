//TODO Need work
// /package hex.api;
//
//import hex.pam.PAM;
//import water.api.*;
//
//public class RegisterRestApi extends AlgoAbstractRegister {
//
//  @Override
//  public void registerEndPoints(RestApiContext context) {
//    // Register k-LIME model builder REST API
//    PAM PAMmb = new PAM(true);
//    registerModelBuilder(context, PAMmb, SchemaServer.getStableVersion());
//
//    // PAM
//    context.registerEndpoint("pam", "POST /99/PAM", PAMHandler.class, "getPAM",
//            "Conduct PAM Clustering");
//
//  }
//
//  @Override
//  public String getName() {
//    return "PAM";
//  }
//
//}
