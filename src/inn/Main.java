package inn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class BadUserException extends IOException {
    public BadUserException(String message) {
        super(message);
    }
}

class User {
    private String name;
    private String ID;

    public User(String name, String ID) throws BadUserException {
        checkID(ID);
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    private void checkID(String currentID) throws BadUserException {
        if (currentID.length() != 12) {
            throw new BadUserException("Неверный ИНН!");
        }
        try {
            Long.parseLong(currentID);
        } catch (NumberFormatException e) {
            throw new BadUserException("Неверный ИНН!");
        }
    }

    @Override
    public String toString() {
        return "Name = '" + name + '\'' +
                ", ID = '" + ID + "'";
    }
}

class Inn {
    ArrayList<User> arrayList;

    public Inn() {
        arrayList = new ArrayList<>();
    }

    public void newUser(User user) throws BadUserException {
        if(this.Contains(user)) {
            throw new BadUserException("This ID is already in database: " + user.getID());
        }
        arrayList.add(user);
    }

    public void printDataBase() {
        for(Object x : arrayList) {
            System.out.println(x);
        }
    }

    public boolean Contains(User x) {
        for (User currentUser : arrayList) {
            if(x.getID().equals(currentUser.getID())) {
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) throws BadUserException {
        Inn inn = new Inn();

        User user1 = new User("Ivan", "394825123295");
        inn.newUser(user1);
        User user2 = new User("Alex", "334424816501");
        inn.newUser(user2);
        User user3 = new User("Oleg", "743248008928");
        inn.newUser(user3);
        User user4 = new User("Artem", "143491055598");
        inn.newUser(user4);

        inn.printDataBase();
        if (inn.Contains(user3)) {
            System.out.println("Пользователь " + user3.getName() + " находится в БД!");
        } else {
            System.out.println("Пользователь " + user3.getName() + " не находится в БД!");
        }

        Scanner scanner = new Scanner(System.in);
        User user5 = new User(scanner.next(), scanner.next());
        inn.newUser(user5);
        inn.printDataBase();
    }
}
