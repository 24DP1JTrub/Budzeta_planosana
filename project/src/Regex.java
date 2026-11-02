public class Regex {
    public boolean checkEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public boolean checkPassword(String password) {
        return password.matches("^.{9,20}$");
    } 

    public boolean checkName(String name) {
        if (1 < name.length() && name.length() < 20) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNumber(String number) {
        return number.matches("^\\d+(\\.\\d{1,2})?$");
    }

}

