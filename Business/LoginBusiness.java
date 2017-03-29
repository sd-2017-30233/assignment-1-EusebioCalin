package Business;

import Persistence.Users;

/**
 * Created by Calin on 23.03.2017.
 */
public class LoginBusiness {

    private AdminBusiness adminBusiness;
    private UserBusiness userBusiness;

    public AdminBusiness getAdminBusiness() {
        return adminBusiness;
    }

    public void setAdminBusiness(AdminBusiness adminBusiness) {
        this.adminBusiness = adminBusiness;
    }

    public UserBusiness getUserBusiness() {
        return userBusiness;
    }

    public void setUserBusiness(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    public static int getUserType(String username, String password)
    {
        Users user = new Users();
        int rezultat=user.findByUserAndPass(username,password);
        return rezultat;
    }
}
