package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User_1", "Lastname_1", "user_1@mail.ru"));
      userService.add(new User("User_2", "Lastname_2", "user_2@mail.ru"));

      User user1 = new User("User_3", "Lastname_3", "user_3@mail.ru");
      User user2 = new User("User_4", "Lastname_4", "user_4@mail.ru");
      Car car1 = new Car("Car_1", 111);
      Car car2 = new Car("Car_2", 222);
      user1.setCar(car1);
      user2.setCar(car2);
      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.getUser(car1));
      context.close();
   }
}
