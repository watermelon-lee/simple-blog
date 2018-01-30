我的第一个javaEE项目，使用jsp&servlet。寒假学完这个，并且完成这个简单的博客

1-20寒假开始了~


1-30 debug了好几天，终于解决了
request.getRequestDispatcher（）可取绝对路径也可以取相对路径，ServletUser中写错了改正之后的自己看看
reques.getPathInfo()，requrst.getServletpath（）是根据自己servlet的urlpattern
如url Pattern：/user/*
访问/Myblog/user/caterpillar
getPathInfo（）=/caterpillar 然后用substring（1）就可得到caterpillar
getServletPath()=/user
所以，我们要获取相对于 request.getContextPath() 的路径，我们可以使用如下的代码：
String uri = request.getServletPath();
String pathInfo = request.getPathInfo();
if (pathInfo != null && pathInfo.length() > 0) {
    uri = uri + pathInfo;
}
servletConfig以及servletContext
在jsp中隐藏对象application对应ServletContext，config对应servletConfig
使用javabean配合EL标签 以及JSTL标签 减少jsp页面中<%%>使用