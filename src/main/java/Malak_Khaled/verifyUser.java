package Malak_Khaled;

import dev.morphia.query.Query;

public class verifyUser {
    public verifyUser() {

    }

    public static User verifyUser(String username, String password) {

        Query query = DB_config.datastore.createQuery(User.class)
                .field("Username").equal(username);
        if (!query.asList().isEmpty()) {
            User user = (User) query.asList().get(0);
            if (user.getPassword().equalsIgnoreCase(password))
                return user;
        }
        return null;
    }
}

