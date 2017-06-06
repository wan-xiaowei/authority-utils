

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
    <version>1.0.0</version>
</dependency>
```

### 代码调用

- 基于 Spring Boot 与 Shiro 框架
- 在 Spring Boot 项目的 Main 类上加上注解：`@ServletComponentScan`，该注解用于扫描：@WebServlet, @WebFilter, @WebListene
- 添加一个 listener：

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
		List<SysPermissionInfo> list = new ShiroAnnotationInfoUtil().getRequestMapping("com.youmeek.springboot.controller");
		this.servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute("shiroAnnotationInfoList", list);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	
	}
}

```

- 在类和方法上添加注解：`@ShiroPermissionInfo`

``` java
import com.youmeek.springboot.annotation.ShiroPermissionInfo;
import com.youmeek.springboot.dto.SysPermissionInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ShiroPermissionInfo(object_id = "SellerCube.Feedback.SysUserController", object_name = "客服系统-用户模块", object_type = "52", data_type = "4")
public class SysUserController {

	/**
	 * 当前系统所有的权限列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/shiroAnnotationListTest", method = RequestMethod.GET)
	@ResponseBody
	public List<SysPermissionInfo> shiroTest(HttpServletRequest request) {
		//获取 shiro 的所有注解信息
		ServletContext servletContext = request.getServletContext();
		return (List<SysPermissionInfo>) servletContext.getAttribute("shiroAnnotationInfoList");
	}

	/**
	 * 权限操作 -- 添加
	 *
	 * @return
	 */
	@RequiresPermissions("SellerCube.Feedback.SysUserController.add")
	@ShiroPermissionInfo(object_id = "SellerCube.Feedback.SysUserController.add", object_name = "客服系统-用户模块-新增", object_type = "53", data_type = "4", parent_id = "SellerCube.Feedback.SysUserController")
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
	@ShiroPermissionInfo(object_id = "SellerCube.Feedback.SysUserController.list", object_name = "客服系统-用户模块-列表", object_type = "53", data_type = "4", parent_id = "SellerCube.Feedback.SysUserController")
	@RequestMapping(value = "/needUserApi/test", method = RequestMethod.GET)
	public String needUserApiTest() {
		return "requestSuccess";
	}
}
```

- 注解：`@ShiroPermissionInfo` 的几个参数说明：
	- `object_id` 为权限的标识符，可以作用在类和方法上。object_id 在类和方法是父子关系，该值不能填写错误，且需要和 @RequiresPermissions 注解一样。
	- `object_name` 为该权限的描述
	- `object_type` 51 为系统命名空间，52 为类命名空间，53 为类中方法命名空间
	- `data_type` java 项目统一设置为 4





















