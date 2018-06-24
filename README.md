# springMVC
## SpringMVC项目简介：
SpringMVC项目主要有以下几个部分组成：（1）基于注解配置SpringMVC在页面输出“Hello Spring MVC”，（2）Spring MVC接受表单数据（3）客户端跳转（4）页面显示session中记录的访问次数例子（5）上传文件（6）拦截器。<br>
### （1）基于注解配置SpringMVC在页面输出“Hello Spring MVC”：<br>
#### 1, 在WEB-INF目录下的web.xml,配置了Spring MVC的入口DispatcherServlet，把所有的请求都提交到该Servlet.<br>
#### 2, 在WEB-INF目录下的springmvc-servlet.xml,与上一步中的<servlet-name>springmvc</servlet-name>对应,在springmvc-servlet.xml中添加<context:component-scan base-package="controller" />表示从包controller下扫描有@Controller注解的类。<br>
#### 3，在springmvc-servlet.xml中添加以下语句进行视图定位：<br>
```
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver"><br>
	<property name="prefix" value="/WEB-INF/page/" /><br>
	<property name="suffix" value=".jsp" /></bean><br>
```
#### 4，在controller包中，对HelloController类加上@Controller注解，表示该类是一个控制器，在方法handleRequest前面加上@RequestMapping("/hello") 表示路径/hello会映射到该方法上。<br>
#### 5，在方法handleRequest中，将"Hello Spring MVC"字符串放入message传给hello.jsp并在页面中显示出来。<br>

### （2）Spring MVC接受表单数据：
#### 1，pojo包下新建实体类Product，主要有id属性和name属性。<br>
#### 2，创建addProduct.jsp用于接收用户输入的id和name，提交到/addProduct路径。<br>
#### 3，controller包下创建控制器ProductController，add方法映射/addProduct路径，为add方法准备一个Product参数，用于接收注入。最后跳转到showProduct页面显示用户提交的数据，在showProduct页面中用EL表达式显示用户提交的名称和价格 。<br>

### （3）客户端跳转：
#### 1，在controller包的HelloController类中增加jump方法，注解@RequestMapping("/jump")表示路径/jump会映射到该方法上。<br>
#### 2，在方法体里ModelAndView mav = new ModelAndView("redirect:/hello")，实现客户端跳转到/hello。<br>

### （4）页面显示session中记录的访问次数例子：
#### 1，在controller包的HelloController类中增加check方法，@RequestMapping("/check")表示路径/check会映射到该方法上。<br>
#### 2，为方法check()提供参数HttpSession session，这样就可以在方法体中使用session了，接下来的逻辑就是每次访问为session中的counter+1。<br>
#### 3，最后跳转到check.jsp页面，显示counter的值。<br>

### （5）上传文件：
#### 1，配置web.xml允许访问*.jpg：在web.xml中新增加一段：<br>
```
 	<servlet-mapping><br>
	    <servlet-name>default</servlet-name><br>
	    <url-pattern>*.jpg</url-pattern><br>
  	</servlet-mapping> <br>
  ```
表示允许访问*.jpg。并且必须加在springmvc的servlet之前。<br>
#### 2， 配置springmvc-servlet.xml：新增加一段配置，开放对上传功能的支持：<br>
```
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/><br>
```
#### 3，upload.jsp上传页面：上传页面，需要注意的是form的两个属性必须提供，method="post" 和 enctype="multipart/form-data" 缺一不可。上传组件，增加一个属性： accept="image/*" 表示只能选择图片进行上传。<br>
#### 4，准备UploadedImageFile：在UploadedImageFile中封装MultipartFile类型的字段 image ，用于接受页面的注入。这里的字段image必须和上传页面upload.jsp中的image
<input type="file" name="image" accept="image/*" />保持一致。<br>
#### 5， UploadController 上传控制器：新建类UploadController 作为上传控制器，准备方法upload 映射上传路径/uploadImage。<br>
 	1）方法的第二个参数UploadedImageFile 中已经注入好了 image<br>
	2）通过 RandomStringUtils.randomAlphanumeric(10);获取一个随机文件名。 因为用户可能上传相同文件名的文件，为了不覆盖原来的文件，通过随机文件名的办法来规避<br>
		3）根据request.getServletContext().getRealPath 获取到web目录下的image目录，用于存放上传后的文件。<br>
	4）调用file.getImage().transferTo(newFile); 复制文件<br>
	5）把生成的随机文件名提交给视图，用于后续的显示。<br>
#### 6，showUploadedFile.jsp 显示图片的页面：在WEB-INF/page 下新建文件showUploadedFile显示上传的图片。<br>

### （6）拦截器：
#### 1，interceptor包中有一个拦截器类HelloInterceptor，相关注释已经写在代码里了。<br>
#### 2，配置拦截器时，修改springmvc-servlet.xml以对/hello路径进行拦截，如果要拦截其他路径：<br>
	/** 拦截所有<br>
	/category/** 拦截/category路径下的所有<br>
#### 3，修改hello.jsp，将拦截器放进去的日期也打印出来。<br>
