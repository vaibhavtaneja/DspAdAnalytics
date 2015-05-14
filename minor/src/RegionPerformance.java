//RegionPerformance




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class RegionWise
 */
@WebServlet("/RegionPerformance")
public class RegionPerformance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegionPerformance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
		Session session = cluster.connect();
		//Cluster cluster2 = Cluster.builder().addContactPoint("localhost").build();
		//Session session2 = cluster2.connect();
		//Cluster cluster3 = Cluster.builder().addContactPoint("localhost").build();
		//Session session3 = cluster2.connect();
		String id = request.getParameter("cityName");
		String tabl = request.getParameter("type");
		out.println("<!DOCTYPE html><html><head><title>Area Wise Analysis</title><link rel='stylesheet' href='extra/main.css'><script  type='text/javascript' src='extra/d3.min.js'></script><link type= 'text/css' rel='stylesheet' href='extra/bootstrap.css'/><link type='text/css' rel='stylesheet' href='extra/divform.css' /><link rel='stylesheet' href='extra/font-awesome-4.3.0/css/font-awesome.min.css'></head><body><div class='navbar navbar-default navbar-fixed-top' role='navigation'><div class= 'container-fluid'><div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></button><a href='#' class='navbar-brand'>Project</a> </div><div class='navbar-collapse'><ul class='nav navbar-nav'><li><a href='#'></a> </li><li><a href='#'></a>  </li><li><a href='#'></a> </li>"
				+ "<li><a href='/minor/BarChart'><i class='fa fa-bar-chart'></i> Advertiser Analysis</a></li>"
				+ "<li><a href='/minor/RegionWise'><i class='fa fa-pie-chart'></i> Area Wise Analysis</a></li>"
				+ "<li><a href='#'><i class='fa fa-th-list'></i> Categorical Distribution</a></li>"
				+ "<li><a href='#'><i class='fa fa-magic'></i> Insightful Algorithms</a></li></ul></div></div></div><br>"
				+ "<div style='margin-top:51px' class='navbar navbar-default navbar-fixed-top' role='navigation'><div class= 'container-fluid'><div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></button><a href='#' class='navbar-brand'>         </a> </div><div class='navbar-collapse'><ul style='margin-left:-2px' class='nav navbar-nav'><li><a href='#'></a><li><a href='#'></a><li><a href='#'></a> </li><li><a href='#'></a>  </li><li><a href='#'></a> </li>"
				+ "<li><a href='/minor/MapAnalysis?#'><i class='fa fa-map-marker'></i>   Map DSPs Analysis      </a></li>"
				+ "<li><a href='/minor/RegionPerformance'><i class='fa fa-users'></i> Region's Standings  </a></li>"
				+ "<li><a href='#'><i class='fa fa-th-list'></i> Category Wise Distribution</a></li>"
				+ "<li><a href='#'><i class='fa fa-magic'></i> Insightful Algorithms</a></li></ul></div></div></div>"
				+ "<br><br><br><br><div id='formbar'><form class='form-horizontal' action='RegionPerformance'><div class='form-group'><label for='city' class='col-sm-3 control-label'><font face='verdana'>Please select City to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='cityName'><option id='0'>beijing</option><option id='1'>tianjin</option><option id='2'>hebei</option><option id='3'>shanxi</option><option id='4'>neimenggu</option><option id='5'>liaoning</option><option id='6'>jilin</option><option id='7'>heilongjiang</option><option id='8'>shanghai</option><option id='9'>jiangsu</option><option id='10'>zhejiang</option><option id='11'>anhui</option><option id='12'>fujian</option><option id='13'>jiangxi</option><option id='14'>	shandong</option><option id='15'>	henan</option><option id='16'>	hubei</option><option id='17'>hunan</option><option id='18'>guangdong</option><option id='19'>guangxi</option><option id='20'>hainan</option><option id='21'>	chongqing</option><option id='22'>sichuan</option><option id='23'>guizhou</option><option id='24'>yunnan</option><option id='25'>xizang</option><option id='26'>shannxi</option><option id='27'>gansu</option><option id='28'>qinghai</option><option id='29'>ningxia</option><option id='30'>xinjiang</option><option id='31'>taiwan</option><option id='32'>xianggang</option><option id='33'>aomen</option></select></div><div class='form-group'><label for='city' class='col-sm-2 control-label'><font face='verdana'>Sector to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='type'><option id='bid'>Bids</option><option id='impression'>Impressions</option><option id='clicks'>Clicks</option><option id='converts'>Converts</option></select></div><div class='col-sm-2'><button type='submit' class='btn btn-primary'>Analyze</button></div></div></form></div>"
				+"<label for='mapcity' id='san'></label><p style='text-align:center'><div id='mapholder'></div></p>");
		//out.println(""+id+" "+tabl);
		if(id!=null){
			//out.println(id+"<br>");
			//out.println(tabl+"<br>");
			String table = "";
			switch(tabl){
				case "Bids":
					table = "bid_r";
					break ;
				case "Impressions":
					table = "imp_r";
					break ;
				case "Clicks":
					table = "click_process";
					break ;
				case "Converts":
					table = "conv_process";
					break ;	
			}
			int [] city_no = {0,1,2,3,15,27,40,55,65,79,80,94,106,124,134,146,164,183,201,216,238,253,275,276,298,308,325,333,344,359,368,374,393,394,395};
		      String [] city_name = {"unknown","beijing","tianjin","hebei","shanxi","neimenggu","liaoning","jilin","heilongjiang","shanghai","jiangsu","zhejiang","anhui","fujian","jiangxi","shandong","henan","hubei","hunan","guangdong","guangxi","hainan","chongqing","sichuan","guizhou","yunnan","xizang","shannxi","gansu","qinghai","ningxia","xinjiang","taiwan","xianggang","aomen"};
		      int city_num=0;
		      
		      for(int j=0;j<city_name.length;j++){
		    	//out.println(city_name[j]+" "+id);  
		        if(city_name[j].equalsIgnoreCase(id)){
		          city_num=city_no[j];
		        }
		      }
		      out.println(city_num);
		      long [] city_count = new long[5];
		      
		      
		      String queryString1 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='1458' AND region='"+city_num+"'LIMIT 800000";
		      ResultSet result1 = session.execute(queryString1);
		      Row row1 = result1.one();
		      city_count[0] = row1.getLong("count");
		      //*
		      String queryString2 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='3358' AND region='"+city_num+"'LIMIT 800000";
		      ResultSet result2 = session.execute(queryString2);
		      row1 = result2.one();
		      city_count[1] = row1.getLong("count");
		      String queryString3 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='3386' AND region='"+city_num+"'LIMIT 800000";
		      ResultSet result3 = session.execute(queryString3);
		      row1 = result3.one();
		      city_count[2] = row1.getLong("count");
		      String queryString4 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='3427' AND region='"+city_num+"'LIMIT 800000";
		      ResultSet result4 = session.execute(queryString4);
		      row1 = result4.one();
		      city_count[3] = row1.getLong("count");
		      String queryString5 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='3476' AND region='"+city_num+"'LIMIT 800000";
		      ResultSet result5 = session.execute(queryString5);
		      row1 = result5.one();
		      city_count[4] = row1.getLong("count");
		      //*/
		      /*
		      for(int k=0;k<city_count.length;k++)
		    	  out.println(""+city_count[k]);
			  //*/	
		      
		      out.println("<script>\n" +
		              "var data = [\n" +
		              " {key: \"1458\",   value: "+city_count[0]+"},\n" +
		              " {key: \"3358\", value: "+city_count[1]+"},\n" +
		              " {key: \"3386\", value: "+city_count[2]+"},\n" +
		              " {key: \"3427\", value: "+city_count[3]+"},\n" +
		              " {key: \"3476\",   value: "+city_count[4]+"},\n" +
		              "];\n" +
		              "var w = 800;\n" +
		              "var h = 450;\n" +
		              "var margin = {\n" +
		              " top: 58,\n" +
		              " bottom: 100,\n" +
		              " left: 80,\n" +
		              " right: 40\n" +
		              "};\n" +
		              "var width = w - margin.left - margin.right;\n" +
		              "var height = h - margin.top - margin.bottom;\n" +
		              "\n" +
		              "var x = d3.scale.ordinal()\n" +
		              "   .domain(data.map(function(entry){\n" +
		              "     return entry.key;\n" +
		              "   }))\n" +
		              "   .rangeBands([0, width]);\n" +
		              "var y = d3.scale.linear()\n" +
		              "   .domain([0, d3.max(data, function(d){\n" +
		              "     return d.value;\n" +
		              "   })])\n" +
		              "   .range([height, 0]);\n" +
		              "var ordinalColorScale = d3.scale.category20();\n" +
		              "var xAxis = d3.svg.axis()\n" +
		              "     .scale(x)\n" +
		              "     .orient(\"bottom\");\n" +
		              "var yAxis = d3.svg.axis()\n" +
		              "     .scale(y)\n" +
		              "     .orient(\"left\");\n" +
		              "var yGridlines = d3.svg.axis()\n" +
		              "       .scale(y)\n" +
		              "       .tickSize(-width,0,0)\n" +
		              "       .tickFormat(\"\")\n" +
		              "       .orient(\"left\");\n" +
		              "var svg = d3.select(\"body\").append(\"svg\")\n" +
		              "     .attr(\"id\", \"chart\")\n" +
		              "     .attr(\"width\", w)\n" +
		              "     .attr(\"height\", h);\n" +
		              "var chart = svg.append(\"g\")\n" +
		              "     .classed(\"display\", true)\n" +
		              "     .attr(\"transform\", \"translate(\" + margin.left + \",\" + margin.top + \")\");\n" +
		              "var controls = d3.select(\"body\")\n" +
		              "       .append(\"div\")\n" +
		              "       .attr(\"id\", \"controls\");\n" +
		              "var sort_btn = controls.append(\"button\")\n" +
		              "       .html(\"Sort data: ascending\")\n" +
		              "       .attr(\"state\", 0);\n" +
		              "function drawAxis(params){\n" +
		              " \n" +
		              " if(params.initialize === true){\n" +
		              "\n" +
		              "   this.append(\"g\")\n" +
		              "     .call(params.gridlines)\n" +
		              "     .classed(\"gridline\", true)\n" +
		              "     .attr(\"transform\", \"translate(0,0)\")\n" +
		              "\n" +
		              "   this.append(\"g\")\n" +
		              "     .classed(\"x axis\", true)\n" +
		              "     .attr(\"transform\", \"translate(\" + 0 + \",\" + height + \")\")\n" +
		              "     .call(params.axis.x)\n" +
		              "       .selectAll(\"text\")\n" +
		              "         .classed(\"x-axis-label\", true)\n" +
		              "         .style(\"text-anchor\", \"end\")\n" +
		              "         .attr(\"dx\", -8)\n" +
		              "         .attr(\"dy\", 8)\n" +
		              "         .attr(\"transform\", \"translate(0,0) rotate(-45)\");\n" +
		              "\n" +
		              "\n" +
		              "   this.append(\"g\")\n" +
		              "     .classed(\"y axis\", true)\n" +
		              "     .attr(\"transform\", \"translate(0,0)\")\n" +
		              "     .call(params.axis.y);\n" +
		              "\n" +
		              "\n" +
		              "   this.select(\".y.axis\")\n" +
		              "     .append(\"text\")\n" +
		              "     .attr(\"x\", 0)\n" +
		              "     .attr(\"y\", 0)\n" +
		              "     .style(\"text-anchor\", \"middle\")\n" +
		              "     .attr(\"transform\", \"translate(-65,\" + height/2 + \") rotate(-90)\")\n" +
		              "     .text(\"Units\");\n" +
		              "\n" +
		              "\n" +
		              "   this.select(\".x.axis\")\n" +
		              "     .append(\"text\")\n" +
		              "     .attr(\"x\", 0)\n" +
		              "     .attr(\"y\", 0)\n" +
		              "     .style(\"text-anchor\", \"middle\")\n" +
		              "     .attr(\"transform\", \"translate(\" + width/2 + \",80)\")\n" +
		              "     .text(\"Ad Success\");\n" +
		              " \n" +
		              " } else if(params.initialize === false){\n" +
		              "\n" +
		              "   this.selectAll(\"g.x.axis\")\n" +
		              "     .transition()\n" +
		              "     .duration(500)\n" +
		              "     .ease(\"bounce\")\n" +
		              "     .delay(500)\n" +
		              "     .call(params.axis.x);\n" +
		              "   this.selectAll(\".x-axis-label\")\n" +
		              "     .style(\"text-anchor\", \"end\")\n" +
		              "     .attr(\"dx\", -8)\n" +
		              "     .attr(\"dy\", 8)\n" +
		              "     .attr(\"transform\", \"translate(0,0) rotate(-45)\");\n" +
		              "   this.selectAll(\"g.y.axis\")\n" +
		              "     .transition()\n" +
		              "     .duration(500)\n" +
		              "     .ease(\"bounce\")\n" +
		              "     .delay(500)\n" +
		              "     .call(params.axis.y);\n" +
		              " }\n" +
		              "}\n" +
		              "function plot(params){\n" +
		              " x.domain(data.map(function(entry){\n" +
		              "     return entry.key;\n" +
		              "   }));\n" +
		              " y.domain([0, d3.max(data, function(d){\n" +
		              "     return d.value;\n" +
		              "   })])\n" +
		              "\n" +
		              "\n" +
		              " drawAxis.call(this, params);\n" +
		              "\n" +
		              " this.selectAll(\".bar\")\n" +
		              "   .data(params.data)\n" +
		              "   .enter()\n" +
		              "     .append(\"rect\")\n" +
		              "     .classed(\"bar\", true);\n" +
		              "\n" +
		              " this.selectAll(\".bar-label\")\n" +
		              "   .data(params.data)\n" +
		              "   .enter()\n" +
		              "     .append(\"text\")\n" +
		              "     .classed(\"bar-label\", true);\n" +
		              " \n" +
		              "\n" +
		              " this.selectAll(\".bar\")\n" +
		              "   .transition()\n" +
		              "   .duration(500)\n" +
		              "   .ease(\"bounce\")\n" +
		              "   .delay(500)\n" +
		              "   .attr(\"x\", function(d,i){\n" +
		              "     return x(d.key);\n" +
		              "   })\n" +
		              "   .attr(\"y\", function(d,i){\n" +
		              "     return y(d.value);\n" +
		              "   })\n" +
		              "   .attr(\"height\", function(d,i){\n" +
		              "     return height - y(d.value);\n" +
		              "   })\n" +
		              "   .attr(\"width\", function(d){\n" +
		              "     return x.rangeBand();\n" +
		              "   })\n" +
		              "   .style(\"fill\", function(d,i){\n" +
		              "     return ordinalColorScale(i);\n" +
		              "   });\n" +
		              "\n" +
		              " this.selectAll(\".bar-label\")\n" +
		              "   .transition()\n" +
		              "   .duration(500)\n" +
		              "   .ease(\"bounce\")\n" +
		              "   .delay(500)\n" +
		              "   .attr(\"x\", function(d,i){\n" +
		              "     return x(d.key) + (x.rangeBand()/2)\n" +
		              "   })\n" +
		              "   .attr(\"dx\", 0)\n" +
		              "   .attr(\"y\", function(d,i){\n" +
		              "     return y(d.value);\n" +
		              "   })\n" +
		              "   .attr(\"dy\", -6)\n" +
		              "   .text(function(d){\n" +
		              "     return d.value;\n" +
		              "   })\n" +
		              "\n" +
		              "\n" +
		              " this.selectAll(\".bar\")\n" +
		              "   .data(params.data)\n" +
		              "   .exit()\n" +
		              "   .remove();\n" +
		              "\n" +
		              " this.selectAll(\".bar-label\")\n" +
		              "   .data(params.data)\n" +
		              "   .exit()\n" +
		              "   .remove();\n" +
		              "}\n" +
		              "\n" +
		              "sort_btn.on(\"click\", function(){\n" +
		              " var self = d3.select(this);\n" +
		              " var ascending = function(a,b){\n" +
		              "   return a.value - b.value;\n" +
		              " };\n" +
		              " var descending = function(a,b){\n" +
		              "   return b.value - a.value;\n" +
		              " }\n" +
		              " var state = +self.attr(\"state\");\n" +
		              " var txt = \"Sort data: \";\n" +
		              " if(state === 0){\n" +
		              "   data.sort(ascending);\n" +
		              "   state = 1;\n" +
		              "   txt += \"descending\";\n" +
		              " } else if(state === 1){\n" +
		              "   data.sort(descending);\n" +
		              "   state = 0;\n" +
		              "   txt += \"ascending\";\n" +
		              " }\n" +
		              " self.attr(\"state\", state);\n" +
		              " self.html(txt);\n" +
		              "\n" +
		              " plot.call(chart, {\n" +
		              "   data: data,\n" +
		              "   axis:{\n" +
		              "     x: xAxis,\n" +
		              "     y: yAxis\n" +
		              "   },\n" +
		              "   gridlines: yGridlines,\n" +
		              "   initialize: false\n" +
		              " });\n" +
		              "});\n" +
		              "\n" +
		              "plot.call(chart, {\n" +
		              " data: data,\n" +
		              " axis:{\n" +
		              "   x: xAxis,\n" +
		              "   y: yAxis\n" +
		              " },\n" +
		              " gridlines: yGridlines,\n" +
		              " initialize: true\n" +
		              "});\n" +
		              "</script>");
		      
		      
		}
		
		out.println("<script src='extra/jquery.js'></script><script src='extra/bootstrap.js'></script></body></html>");
		
	}

}
