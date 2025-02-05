package mapper.impl;

import mapper.Mapper;
import mapper.Numeric;
import model.User;

public class UserMapper implements Mapper<User, String> , Numeric {

    @Override
    public String toInput(User user) {
        String format = String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getCreationDateTime());
        return format;
    }

    @Override
    public User toOutput(String s) {
        String[] lines = s.split(",");
        long id;
        if (isDigit(lines[0])) {
            id = Long.parseLong(lines[0]);
            return new User(id, lines[1], lines[2], lines[3]);
        }
        throw new NumberFormatException("Id must be a large number");
    }

    @Override
    public boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
