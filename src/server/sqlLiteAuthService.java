package server;

public class sqlLiteAuthService implements AuthService{

    BD bd;

    public sqlLiteAuthService(BD bd) {
        this.bd = bd;
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        return bd.getNickByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        if(!bd.isExistUserByLoginORNickname(login,nickname)){
            bd.saveUser(login,password,nickname);
            return true;
        }
        else return false;
    }
}
