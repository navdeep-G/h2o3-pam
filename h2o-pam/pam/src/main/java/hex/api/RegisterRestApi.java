package hex.api;

import hex.pam.PAM;
import water.api.*;

public class RegisterRestApi extends AlgoAbstractRegister {

  @Override
  public void registerEndPoints(RestApiContext context) {
      PAM pam = new PAM(true);
      registerModelBuilder(context, pam, SchemaServer.getStableVersion());
  }

  @Override
  public String getName() {
    return "PAM";
  }

}
