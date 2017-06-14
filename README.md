

# 使用方法

## 后台开发者

### maven 仓库

``` xml
<mirrors>
    <mirror>
        <id>nexus-releases</id>
        <mirrorOf>*</mirrorOf>
        <url>http://192.168.1.73:8081/repository/maven-releases/</url>
    </mirror>
</mirrors>
```

### pom.xml 依赖

``` xml
<dependency>
    <groupId>com.skb.erp</groupId>
    <artifactId>skb-authority-utils</artifactId>
    <version>1.0.3</version>
</dependency>
```

### 代码调用

- 基于 Spring Boot 与 Shiro 框架
- 1. 在 Spring Boot 项目的 Main 类上加上注解：`@ServletComponentScan`，该注解用于扫描：@WebServlet, @WebFilter, @WebListene
- 2. 添加一个 listener：

``` java
import com.youmeek.springboot.dto.SysPermissionInfo;
import com.youmeek.springboot.utils.ShiroAnnotationInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

//自定义一个监听器方法
@WebListener 
public class ShiroAnnotationInfoListener implements ServletContextListener {

	private ServletContext servletContext;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String systemCode = "SellerCube.Feedback";
		List<SysPermissionInfo> list = new ShiroAnnotationInfoUtil().getRequestMapping("com.example.demo.controller",systemCode);
		this.servletContext = servletContextEvent.getServletContext();

		SysPermissionObject sysPermissionObject = new SysPermissionObject();
		sysPermissionObject.setRouteKey(systemCode);
		sysPermissionObject.setSystemName("财务系统");
		sysPermissionObject.setItems(list);
		
		servletContext.setAttribute("sysPermissionObject", sysPermissionObject);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	
	}
}

```

- 3. 在需要用到权限控制的 Controller 上，对类和方法上添加注解：`@ShiroPermissionInfo`

``` java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
@ShiroPermissionInfo(itemName = "财务系统-用户模块")
public class SysUserController {

	/**
	 * 当前系统所有的权限列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/systemAuthList", method = RequestMethod.GET)
	@ResponseBody
	public SysPermissionObject systemAuthList(HttpServletRequest request) {
		//获取 shiro 的所有注解信息
		ServletContext servletContext = request.getServletContext();
		return (SysPermissionObject) servletContext.getAttribute("sysPermissionObject");
	}

	/**
	 * 权限操作 -- 添加
	 *
	 * @return
	 */
	@ShiroPermissionInfo(itemName = "财务系统-用户模块-新增")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "requestSuccess";
	}

	/**
	 * 权限操作 -- 列表
	 *
	 * @return
	 */
	@ShiroPermissionInfo(itemName = "财务系统-用户模块-列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "requestSuccess";
	}
}
```

- 注解：`@ShiroPermissionInfo` 的几个参数说明：
	- `itemId` 为权限的标识符（非必填），可以作用在类和方法上。itemId 可以为空，为空的时候取当前类路径+类名+方法名
	- `itemName` 为该权限的描述（必填）

- 最终权限中心拿到的 JSON 格式为：

``` json
{
  routeKey: "SellerCube.Feedback",
  systemName: "财务系统",
  items: [
    {
      itemId: "SellerCube.Feedback.com.skb.user.SysUserController",
      itemName: "财务系统-用户模块"
    },
    {
      itemId: "SellerCube.Feedback.com.skb.user.SysUserController.list",
      itemName: "财务系统-用户模块-列表"
    },
    {
      itemId: "SellerCube.Feedback.com.skb.user.SysUserController.add",
      itemName: "财务系统-用户模块-新增"
    }
  ]
}
```



















