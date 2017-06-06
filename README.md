

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
    <version>1.0.1</version>
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
		List<SysPermissionInfo> list = new ShiroAnnotationInfoUtil().getRequestMapping("com.example.demo.controller");
		this.servletContext = servletContextEvent.getServletContext();

		SysPermissionObject sysPermissionObject = new SysPermissionObject();
		sysPermissionObject.setRouteKey("SellerCube.Feedback");
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
import com.skb.authority.annotation.ShiroPermissionInfo;
import com.skb.authority.dto.SysPermissionObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
@ShiroPermissionInfo(itemId = "SellerCube.Feedback.SysUserController", itemName = "财务系统-用户模块")
public class SysUserController {

	/**
	 * 当前系统所有的权限列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/shiroAnnotationListTest", method = RequestMethod.GET)
	@ResponseBody
	public SysPermissionObject shiroTest(HttpServletRequest request) {
		//获取 shiro 的所有注解信息
		ServletContext servletContext = request.getServletContext();
		return (SysPermissionObject) servletContext.getAttribute("sysPermissionObject");
	}

	/**
	 * 权限操作 -- 添加
	 *
	 * @return
	 */
	@RequiresPermissions("SellerCube.Feedback.SysUserController.add")
	@ShiroPermissionInfo(itemId = "SellerCube.Feedback.SysUserController.add", itemName = "财务系统-用户模块-新增", object_type = "53", data_type = "4", parent_id = "SellerCube.Feedback.SysUserController")
	@RequestMapping(value = "/needAuthcApi/test", method = RequestMethod.GET)
	public String needAuthcApiTest() {
		return "requestSuccess";
	}

	/**
	 * 权限操作 -- 列表
	 *
	 * @return
	 */
	@RequiresPermissions("SellerCube.Feedback.SysUserController.list")
	@ShiroPermissionInfo(itemId = "SellerCube.Feedback.SysUserController.list", itemName = "财务系统-用户模块-列表", object_type = "53", data_type = "4", parent_id = "SellerCube.Feedback.SysUserController")
	@RequestMapping(value = "/needUserApi/test", method = RequestMethod.GET)
	public String needUserApiTest() {
		return "requestSuccess";
	}
}
```

- 注解：`@ShiroPermissionInfo` 的几个参数说明：
	- `itemId` 为权限的标识符，可以作用在类和方法上。itemId 需要和 @RequiresPermissions 注解内容一样。
	- `itemName` 为该权限的描述

- 最终权限中心拿到的 JSON 格式为：

``` json
{
  routeKey: "SellerCube.Feedback",
  systemName: "财务系统",
  items: [
    {
      itemId: "SellerCube.Feedback.SysUserController",
      itemName: "财务系统-用户模块"
    },
    {
      itemId: "SellerCube.Feedback.SysUserController.list",
      itemName: "财务系统-用户模块-列表"
    },
    {
      itemId: "SellerCube.Feedback.SysUserController.add",
      itemName: "财务系统-用户模块-新增"
    }
  ]
}
```



















