package Malak_Khaled;

import dev.morphia.query.Query;

public class verifyManger {

    public void verifyManger(){}

    public static manger verifyManger(String username, String password) {

        Query query = DB_config.datastore.createQuery(manger.class)
                .field("Username").equal(username);
        if (!query.asList().isEmpty()) {
            manger manger = (manger) query.asList().get(0);
            if (manger.getPassword().equalsIgnoreCase(password))
                return manger;
        }
        return null;
    }
}
