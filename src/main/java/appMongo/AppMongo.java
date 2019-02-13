package appMongo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import mongo.entity.UserInfo;
import mongo.service.UserService;

public class AppMongo {

	public static void main(String[] args) {
		new AppMongo().TestQuery();
	}

	private void TestQuery() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("springmgo.xml");
		UserService service = (UserService) ctx.getBean(UserService.class);
		UserInfo user = new UserInfo();
		user.setName("王五");
		user.setAge(29);
		user.setBirth(new Timestamp(System.currentTimeMillis()));
		List<UserInfo> list = null; 
		try {
			list = service.findAll();
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
		}
		System.out.println("查询到:" + list.size());

		ctx.close();
	}

	private static void TestSave() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("springmgo.xml");
		UserService service = (UserService) ctx.getBean(UserService.class);
		UserInfo user = new UserInfo();
		user.setName("王五");
		user.setAge(29);
		user.setBirth(new Timestamp(System.currentTimeMillis()));

		try {
			service.save(user);
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
		}
		System.out.println("已生成ID:" + user.getId());

		ctx.close();
	}

}
