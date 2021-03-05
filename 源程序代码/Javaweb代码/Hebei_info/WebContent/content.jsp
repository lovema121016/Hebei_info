<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.dao.InformationDao"%>
<%@ page import="com.model.Information"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>GARO ESTATE | Property  page</title>
        <meta name="description" content="company is a real-estate template">
        <meta name="author" content="Kimarotec">
        <meta name="keyword" content="html5, css, bootstrap, property, real-estate theme , bootstrap template">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="icon" href="favicon.ico" type="image/x-icon">

        <link rel="stylesheet" href="assets/css/normalize.css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/fontello.css">
        <link href="assets/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
        <link href="assets/fonts/icon-7-stroke/css/helper.css" rel="stylesheet">
        <link href="assets/css/animate.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" href="assets/css/bootstrap-select.min.css"> 
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/icheck.min_all.css">
        <link rel="stylesheet" href="assets/css/price-range.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.css">  
        <link rel="stylesheet" href="assets/css/owl.theme.css">
        <link rel="stylesheet" href="assets/css/owl.transitions.css">
        <link rel="stylesheet" href="assets/css/lightslider.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
    </head>
    <body>

        <div id="preloader">
            <div id="status">&nbsp;</div>
        </div>
        <!-- Body content -->

        <div class="header-connect">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 col-sm-8  col-xs-12">
                        <div class="header-half header-call">
                            <p>
                                <span><i class="pe-7s-call"></i> 15613155379</span>
                                <span><i class="pe-7s-mail"></i> 1328040536@qq.com</span>
                            </p>
                        </div>
                    </div>
                    <div class="col-md-2 col-md-offset-5  col-sm-3 col-sm-offset-1  col-xs-12">
                        <div class="header-half header-social">
                            <ul class="list-inline">
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-vine"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
        <!--End top header -->

        <nav class="navbar navbar-default ">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html"><img src="assets/img/logo.png" alt=""></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse yamm" id="navigation">
                    <div class="button navbar-right">
                        <button class="navbar-btn nav-button wow bounceInRight login" onclick=" window.open('register.html')" data-wow-delay="0.45s">Login</button>
                        <button class="navbar-btn nav-button wow fadeInRight" onclick=" window.open('submit-property.html')" data-wow-delay="0.48s">Submit</button>
                    </div>
                    <ul class="main-nav nav navbar-nav navbar-right">
                    <li class="wow fadeInDown" data-wow-delay="0.5s"><a href="index.jsp">首页</a></li>
                        <li class="dropdown ymm-sw " data-wow-delay="0.1s">
                            <a href="index.html" class="dropdown-toggle active" data-toggle="dropdown" data-hover="dropdown" data-delay="200">自动分类 <b class="caret"></b></a>
                            <ul class="dropdown-menu navbar-nav">
                                <li>
                                    <a href="index.jsp?auto_type=数据">数据</a>
                                </li>
                                <li>
                                    <a href="index.jsp?auto_type=信息">信息</a>
                                </li>
                                <li>
                                    <a href="index.jsp?auto_type=网络">网络</a>
                                </li>
                                <li>
                                    <a href="index.jsp?auto_type=用户">用户</a>
                                </li>
                                <li>
                                    <a href="index.jsp?auto_type=计算机">计算机</a>
                                </li>
                                <li>
                                    <a href="index.jsp?auto_type=系统">系统</a>
                                </li>
                                <li>
                                    <a href="index.jsp?auto_type=其他">其他</a>
                                </li>

                            </ul>
                        </li>

                        <li class="dropdown yamm-fw" data-wow-delay="0.4s">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">分类 <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <div class="yamm-content">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h5>新技术篇</h5>
                                                <ul>
                                                    <li>
                                                        <a href="index.jsp?select=信息化热词">信息化热词</a>
                                                    </li>
                                                    <li>
                                                        <a href="index.jsp?select=大数据">大数据</a>
                                                    </li>
                                                    <li>
                                                        <a href="index.jsp?select=物联网">物联网</a>
                                                    </li>
                                                    <li>
                                                        <a href="index.jsp?select=云计算">云计算</a>
                                                    </li>
                                                    <li>
                                                        <a href="index.jsp?select=区块链">区块链</a>
                                                    </li>
                                                    <li>
                                                        <a href="index.jsp?select=智慧城市">智慧城市</a>
                                                    </li>
                                                    <li>
                                                        <a href="index.jsp?select=工业互联网">工业互联网</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>网络安全篇</h5>
                                                <ul>
                                                    <li><a href="index.jsp?select=信息安全">信息安全</a>  </li>
                                                    <li><a href="index.jsp?select=计算机病毒的防范">计算机病毒的防范</a>  </li>
                                                    <li><a href="index.jsp?select=黑客与入侵检测">黑客与入侵检测</a>  </li>
                                                    <li><a href="index.jsp?select=信息系统测评">信息系统测评</a>  </li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>基础篇</h5>
                                                <ul>
                                                    <li><a href="index.jsp?select=计算机基础">计算机基础</a> </li>
                                                    <li><a href="index.jsp?select=操作系统">操作系统</a> </li>
                                                    <li><a href="index.jsp?select=数据库">数据库</a> </li>
                                                    <li><a href="index.jsp?select=计算机网络">计算机网络</a> </li>
                                                </ul>
                                             
                                            </div>
                                            <div class="col-sm-3">
                                                <h5>国家信息化政策规划篇</h5>
                                                <ul> 
                                                    <li><a href="index.jsp?select=国家信息战略规划">国家信息战略规划</a> </li> 
                                                    <li><a href="index.jsp?select=信息安全相关的法律法规">信息安全相关的法律法规</a> </li> 
                                                </ul>
                                                
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.yamm-content -->
                                </li>
                                </ul>
                        </li>
<%
                        int index=Integer.valueOf(request.getParameter("id"));
                		InformationDao indao=new InformationDao();
                        Information info=indao.load(index);
%>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- End of nav bar -->
       <%
          
          
          
       %>
        <!-- property area -->
        <div class="content-area single-property" style="background-color: #FCFCFC;">&nbsp;
            <div class="container">   

                <div class="clearfix padding-top-40" >

                    <div class="col-md-8 single-property-content prp-style-1 ">
                       

                        <div class="single-property-wrapper">
                            <div class="single-property-header">                                          
                                <h1 class="property-title pull-left"><%=info.getTitle() %></h1>
                            </div>

                            <!-- .property-meta -->

                            <div class="section">
                                <h4 class="s-property-title">概念描述</h4>
                                <div class="s-property-content">
                                    <p><%=info.getContent() %></p>
                                </div>
                            </div>
                            <!-- End description area  -->

                            
                            

                            
                        </div>
                    </div>


                    
                </div>

            </div>
        </div>
    
        <script src="assets/js/vendor/modernizr-2.6.2.min.js"></script>
        <script src="assets/js/jquery-1.10.2.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-select.min.js"></script>
        <script src="assets/js/bootstrap-hover-dropdown.js"></script>
        <script src="assets/js/easypiechart.min.js"></script>
        <script src="assets/js/jquery.easypiechart.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/wow.js"></script>
        <script src="assets/js/icheck.min.js"></script>
        <script src="assets/js/price-range.js"></script>
        <script type="text/javascript" src="assets/js/lightslider.min.js"></script>
        <script src="assets/js/main.js"></script>

        <script>
            $(document).ready(function () {

                $('#image-gallery').lightSlider({
                    gallery: true,
                    item: 1,
                    thumbItem: 9,
                    slideMargin: 0,
                    speed: 500,
                    auto: true,
                    loop: true,
                    onSliderLoad: function () {
                        $('#image-gallery').removeClass('cS-hidden');
                    }
                });
            });
        </script>

    </body>
</html>