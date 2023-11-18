package bookapp;

class PremiumUser extends User {
    private String[] mybooks = new String[5];

    public String[] getMybooks() {
        return mybooks;
    }

    public void setMybooks(String[] mybooks) {
        this.mybooks = mybooks;
    }

    public PremiumUser() {
    }
}