

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
//*/
/**
 * Servlet implementation class BarChart
 */
@WebServlet("/BarChart")
public class BarChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
		Session session = cluster.connect();
		Cluster cluster2 = Cluster.builder().addContactPoint("localhost").build();
		Session session2 = cluster2.connect();
		Cluster cluster3 = Cluster.builder().addContactPoint("localhost").build();
		Session session3 = cluster2.connect();
		String id = request.getParameter("options");
		out.println("<!DOCTYPE html><html><head><title>BarChart</title><link rel='stylesheet' href='extra/main.css'><script  type='text/javascript' src='extra/d3.min.js'></script><link type= 'text/css' rel='stylesheet' href='extra/bootstrap.css'/><link type='text/css' rel='stylesheet' href='extra/divform.css' /><link rel='stylesheet' href='extra/font-awesome-4.3.0/css/font-awesome.min.css'></head><body><div class='navbar navbar-default navbar-fixed-top' role='navigation'><div class= 'container-fluid'><div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></button><a href='#' class='navbar-brand'>Project</a> </div><div class='navbar-collapse'><ul class='nav navbar-nav'><li><a href='#'></a> </li><li><a href='#'></a>  </li><li><a href='#'></a> </li><li><a href='/minor/BarChart'><i class='fa fa-bar-chart'></i> Bar chart</a></li><li><a href='/minor/BarChart'><i class='fa fa-th'></i> Region Wise Distribution</a></li><li><a href='#'><i class='fa fa-th-list'></i> Category Wise Distribution</a></li><li><a href='#'><i class='fa fa-magic'></i> Insightful Algorithms</a></li></ul></div></div></div><br><br><div id='formbar'><form action='BarChart' class='form-horizontal'><div class='form-group'><label for='city' class='col-sm-3 control-label'><font face='verdana'>Please select Ad Id to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='options'><option id='1458' >1458</option><option id='3358' >3358</option><option id='3386' >3386</option><option id='3427'>3427</option><option id='3476'>3476</option></select></div><div class='col-sm-7'><button type='submit' class='btn btn-primary'>Analyze</button></div></div>");
		
		if(id!=null){
			out.println(id+"<br>");
			out.println("c1"+"<br>");
			String queryString1 = "SELECT count (*) FROM ad_db.bid_pro1 where advertiser_id='"+id+"'LIMIT 800000";
			String queryString12 = "SELECT count (*) FROM ad_db.bid_pro2 where advertiser_id='"+id+"'LIMIT 800000";
			String queryString13 = "SELECT count (*) FROM ad_db.bid_pro3 where advertiser_id='"+id+"'LIMIT 800000";
			String queryString2 = "SELECT count (*) FROM ad_db.imp_process where advertiser_id='"+id+"' LIMIT 5000000";
			String queryString3 = "SELECT count (*) FROM ad_db.click_process where advertiser_id='"+id+"'";
			String queryString4 = "SELECT count (*) FROM ad_db.conv_process where advertiser_id='"+id+"'";
			
			ResultSet result1 = session.execute(queryString1);
			ResultSet result2 = session.execute(queryString2);
			ResultSet result3 = session.execute(queryString3);
			ResultSet result4 = session.execute(queryString4);
			
			Row row1 = result1.one();
			long c1 = row1.getLong("count");
			//*
			Row row2 = result2.one();
			long c2 = row2.getLong("count");
			Row row3 = result3.one();
			long c3 = row3.getLong("count");
			Row row4 = result4.one();
			long c4 = row4.getLong("count");
			
			ResultSet result5 = session2.execute(queryString12);
			Row row7 = result5.one();
			long c7 = row7.getLong("count");
			ResultSet result6 = session3.execute(queryString13);
			Row row8 = result6.one();
			long c8 = row8.getLong("count");
			long c9 =c1+c7+c8;
			out.println((c9)+"<br>");
			out.println(c2+"<br>");
			out.println(c3+"<br>");
			out.println(c4+"<br>");
			//*/
			//out.println("<div>");
			//out.println("<script> var data = [{key: 'Bid',value:" +c9+"},{key: '',value: 0},{key: 'Impression',value:" +c2+"},{key: '',	value: 0},{key: 'Click',value:"+c3+"},{key:'',	value: 0},{key: 'Conversion', 	value: "+c4+"},{key: '', 	value: 0}];");
			//out.println("var w = 800;var h = 450;var margin = { top: 58, bottom: 100, left: 80, right: 40};var width = w - margin.left - margin.right;var height = h - margin.top - margin.bottom;var x = d3.scale.ordinal() .domain(data.map(function(entry){ return entry.key; })) .rangeBands([0, width]);var y = d3.scale.linear() .domain([0, d3.max(data, function(d){ return d.value; })]) .range([height, 0]);var ordinalColorScale = d3.scale.category20();var xAxis = d3.svg.axis() .scale(x) .orient(\"bottom\");var yAxis = d3.svg.axis() .scale(y) .orient(\"left\");var yGridlines = d3.svg.axis() .scale(y) .tickSize(-width,0,0) .tickFormat(\"\") .orient(\"left\");var svg = d3.select(\"body\").append(\"svg\") .attr(\"id\", \"chart\") .attr(\"width\", w) .attr(\"height\", h);var chart = svg.append(\"g\") .classed(\"display\", true) .attr(\"transform\", \"translate(\" + margin.left + \",\" + margin.top + \")\");var controls = d3.select(\"body\") .append(\"div\") .attr(\"id\", \"controls\");var sort_btn = controls.append(\"button\") .html(\"Sort data: ascending\") .attr(\"state\", 0);function drawAxis(params){ if(params.initialize === true){ //Draw the gridlines and axes //Draw the gridlines this.append(\"g\") .call(params.gridlines) .classed(\"gridline\", true) .attr(\"transform\", \"translate(0,0)\") //This is the x axis this.append(\"g\") .classed(\"x axis\", true) .attr(\"transform\", \"translate(\" + 0 + \",\" + height + \")\") .call(params.axis.x) .selectAll(\"text\") .classed(\"x-axis-label\", true) .style(\"text-anchor\", \"end\") .attr(\"dx\", -8) .attr(\"dy\", 8) .attr(\"transform\", \"translate(0,0) rotate(-45)\"); //This is the y axis this.append(\"g\") .classed(\"y axis\", true) .attr(\"transform\", \"translate(0,0)\") .call(params.axis.y); //This is the y label this.select(\".y.axis\") .append(\"text\") .attr(\"x\", 0) .attr(\"y\", 0) .style(\"text-anchor\", \"middle\") .attr(\"transform\", \"translate(-50,\" + height/2 + \") rotate(-90)\") .text(\"Units sold\"); //This is the x label this.select(\".x.axis\") .append(\"text\") .attr(\"x\", 0) .attr(\"y\", 0) .style(\"text-anchor\", \"middle\") .attr(\"transform\", \"translate(\" + width/2 + \",80)\") .text(\"Donut type\"); } else if(params.initialize === false){ //Update info this.selectAll(\"g.x.axis\") .transition() .duration(500) .ease(\"bounce\") .delay(500) .call(params.axis.x); this.selectAll(\".x-axis-label\") .style(\"text-anchor\", \"end\") .attr(\"dx\", -8) .attr(\"dy\", 8) .attr(\"transform\", \"translate(0,0) rotate(-45)\"); this.selectAll(\"g.y.axis\") .transition() .duration(500) .ease(\"bounce\") .delay(500) .call(params.axis.y); } } function plot(params){ x.domain(data.map(function(entry){ return entry.key; })); y.domain([0, d3.max(data, function(d){ return d.value; })]) //Draw the axes and axes labels drawAxis.call(this, params); //enter() this.selectAll(\".bar\") .data(params.data) .enter() .append(\"rect\") .classed(\"bar\", true); this.selectAll(\".bar-label\") .data(params.data) .enter() .append(\"text\") .classed(\"bar-label\", true); //update this.selectAll(\".bar\") .transition() .duration(500) .ease(\"bounce\") .delay(500) .attr(\"x\", function(d,i){ return x(d.key); }) .attr(\"y\", function(d,i){ return y(d.value); }) .attr(\"height\", function(d,i){ return height - y(d.value); }) .attr(\"width\", function(d){ return x.rangeBand(); }) .style(\"fill\", function(d,i){ return ordinalColorScale(i); }); this.selectAll(\".bar-label\") .transition() .duration(500) .ease(\"bounce\") .delay(500) .attr(\"x\", function(d,i){ return x(d.key) + (x.rangeBand()/2) }) .attr(\"dx\", 0) .attr(\"y\", function(d,i){ return y(d.value); }) .attr(\"dy\", -6) .text(function(d){ return d.value; }) //exit() this.selectAll(\".bar\") .data(params.data) .exit() .remove(); this.selectAll(\".bar-label\") .data(params.data) .exit() .remove(); } sort_btn.on(\"click\", function(){ var self = d3.select(this); var ascending = function(a,b){ return a.value - b.value; }; var descending = function(a,b){ return b.value - a.value; } var state = +self.attr(\"state\"); var txt = \"Sort data: \"; if(state === 0){ data.sort(ascending); state = 1; txt += \"descending\"; } else if(state === 1){ data.sort(descending); state = 0; txt += \"ascending\"; } self.attr(\"state\", state); self.html(txt); plot.call(chart, { data: data, axis:{ x: xAxis, y: yAxis }, gridlines: yGridlines, initialize: false }); }); plot.call(chart, { data: data, axis:{ x: xAxis, y: yAxis }, gridlines: yGridlines, initialize: true });");
			out.println("<script>\n" +
					"var data = [\n" +
					"	{key: \"Bid\",		value: "+c9+"},\n" +
					"	{key: \"Impression\",	value: "+c2+"},\n" +
					"	{key: \"Click\",	value: "+c3+"},\n" +
					"	{key: \"Conversion\",		value: "+c4+"},\n" +
					"	{key: \" \", 	value: 0}\n" +
					"];\n" +
					"var w = 800;\n" +
					"var h = 450;\n" +
					"var margin = {\n" +
					"	top: 58,\n" +
					"	bottom: 100,\n" +
					"	left: 80,\n" +
					"	right: 40\n" +
					"};\n" +
					"var width = w - margin.left - margin.right;\n" +
					"var height = h - margin.top - margin.bottom;\n" +
					"\n" +
					"var x = d3.scale.ordinal()\n" +
					"		.domain(data.map(function(entry){\n" +
					"			return entry.key;\n" +
					"		}))\n" +
					"		.rangeBands([0, width]);\n" +
					"var y = d3.scale.linear()\n" +
					"		.domain([0, d3.max(data, function(d){\n" +
					"			return d.value;\n" +
					"		})])\n" +
					"		.range([height, 0]);\n" +
					"var ordinalColorScale = d3.scale.category20();\n" +
					"var xAxis = d3.svg.axis()\n" +
					"			.scale(x)\n" +
					"			.orient(\"bottom\");\n" +
					"var yAxis = d3.svg.axis()\n" +
					"			.scale(y)\n" +
					"			.orient(\"left\");\n" +
					"var yGridlines = d3.svg.axis()\n" +
					"				.scale(y)\n" +
					"				.tickSize(-width,0,0)\n" +
					"				.tickFormat(\"\")\n" +
					"				.orient(\"left\");\n" +
					"var svg = d3.select(\"body\").append(\"svg\")\n" +
					"			.attr(\"id\", \"chart\")\n" +
					"			.attr(\"width\", w)\n" +
					"			.attr(\"height\", h);\n" +
					"var chart = svg.append(\"g\")\n" +
					"			.classed(\"display\", true)\n" +
					"			.attr(\"transform\", \"translate(\" + margin.left + \",\" + margin.top + \")\");\n" +
					"var controls = d3.select(\"body\")\n" +
					"				.append(\"div\")\n" +
					"				.attr(\"id\", \"controls\");\n" +
					"var sort_btn = controls.append(\"button\")\n" +
					"				.html(\"Sort data: ascending\")\n" +
					"				.attr(\"state\", 0);\n" +
					"function drawAxis(params){\n" +
					"	\n" +
					"	if(params.initialize === true){\n" +
					"\n" +
					"		this.append(\"g\")\n" +
					"			.call(params.gridlines)\n" +
					"			.classed(\"gridline\", true)\n" +
					"			.attr(\"transform\", \"translate(0,0)\")\n" +
					"\n" +
					"		this.append(\"g\")\n" +
					"			.classed(\"x axis\", true)\n" +
					"			.attr(\"transform\", \"translate(\" + 0 + \",\" + height + \")\")\n" +
					"			.call(params.axis.x)\n" +
					"				.selectAll(\"text\")\n" +
					"					.classed(\"x-axis-label\", true)\n" +
					"					.style(\"text-anchor\", \"end\")\n" +
					"					.attr(\"dx\", -8)\n" +
					"					.attr(\"dy\", 8)\n" +
					"					.attr(\"transform\", \"translate(0,0) rotate(-45)\");\n" +
					"\n" +
					"\n" +
					"		this.append(\"g\")\n" +
					"			.classed(\"y axis\", true)\n" +
					"			.attr(\"transform\", \"translate(0,0)\")\n" +
					"			.call(params.axis.y);\n" +
					"\n" +
					"\n" +
					"		this.select(\".y.axis\")\n" +
					"			.append(\"text\")\n" +
					"			.attr(\"x\", 0)\n" +
					"			.attr(\"y\", 0)\n" +
					"			.style(\"text-anchor\", \"middle\")\n" +
					"			.attr(\"transform\", \"translate(-65,\" + height/2 + \") rotate(-90)\")\n" +
					"			.text(\"Units\");\n" +
					"\n" +
					"\n" +
					"		this.select(\".x.axis\")\n" +
					"			.append(\"text\")\n" +
					"			.attr(\"x\", 0)\n" +
					"			.attr(\"y\", 0)\n" +
					"			.style(\"text-anchor\", \"middle\")\n" +
					"			.attr(\"transform\", \"translate(\" + width/2 + \",80)\")\n" +
					"			.text(\"Ad Success\");\n" +
					"	\n" +
					"	} else if(params.initialize === false){\n" +
					"\n" +
					"		this.selectAll(\"g.x.axis\")\n" +
					"			.transition()\n" +
					"			.duration(500)\n" +
					"			.ease(\"bounce\")\n" +
					"			.delay(500)\n" +
					"			.call(params.axis.x);\n" +
					"		this.selectAll(\".x-axis-label\")\n" +
					"			.style(\"text-anchor\", \"end\")\n" +
					"			.attr(\"dx\", -8)\n" +
					"			.attr(\"dy\", 8)\n" +
					"			.attr(\"transform\", \"translate(0,0) rotate(-45)\");\n" +
					"		this.selectAll(\"g.y.axis\")\n" +
					"			.transition()\n" +
					"			.duration(500)\n" +
					"			.ease(\"bounce\")\n" +
					"			.delay(500)\n" +
					"			.call(params.axis.y);\n" +
					"	}\n" +
					"}\n" +
					"function plot(params){\n" +
					"	x.domain(data.map(function(entry){\n" +
					"			return entry.key;\n" +
					"		}));\n" +
					"	y.domain([0, d3.max(data, function(d){\n" +
					"			return d.value;\n" +
					"		})])\n" +
					"\n" +
					"\n" +
					"	drawAxis.call(this, params);\n" +
					"\n" +
					"	this.selectAll(\".bar\")\n" +
					"		.data(params.data)\n" +
					"		.enter()\n" +
					"			.append(\"rect\")\n" +
					"			.classed(\"bar\", true);\n" +
					"\n" +
					"	this.selectAll(\".bar-label\")\n" +
					"		.data(params.data)\n" +
					"		.enter()\n" +
					"			.append(\"text\")\n" +
					"			.classed(\"bar-label\", true);\n" +
					"	\n" +
					"\n" +
					"	this.selectAll(\".bar\")\n" +
					"		.transition()\n" +
					"		.duration(500)\n" +
					"		.ease(\"bounce\")\n" +
					"		.delay(500)\n" +
					"		.attr(\"x\", function(d,i){\n" +
					"			return x(d.key);\n" +
					"		})\n" +
					"		.attr(\"y\", function(d,i){\n" +
					"			return y(d.value);\n" +
					"		})\n" +
					"		.attr(\"height\", function(d,i){\n" +
					"			return height - y(d.value);\n" +
					"		})\n" +
					"		.attr(\"width\", function(d){\n" +
					"			return x.rangeBand();\n" +
					"		})\n" +
					"		.style(\"fill\", function(d,i){\n" +
					"			return ordinalColorScale(i);\n" +
					"		});\n" +
					"\n" +
					"	this.selectAll(\".bar-label\")\n" +
					"		.transition()\n" +
					"		.duration(500)\n" +
					"		.ease(\"bounce\")\n" +
					"		.delay(500)\n" +
					"		.attr(\"x\", function(d,i){\n" +
					"			return x(d.key) + (x.rangeBand()/2)\n" +
					"		})\n" +
					"		.attr(\"dx\", 0)\n" +
					"		.attr(\"y\", function(d,i){\n" +
					"			return y(d.value);\n" +
					"		})\n" +
					"		.attr(\"dy\", -6)\n" +
					"		.text(function(d){\n" +
					"			return d.value;\n" +
					"		})\n" +
					"\n" +
					"\n" +
					"	this.selectAll(\".bar\")\n" +
					"		.data(params.data)\n" +
					"		.exit()\n" +
					"		.remove();\n" +
					"\n" +
					"	this.selectAll(\".bar-label\")\n" +
					"		.data(params.data)\n" +
					"		.exit()\n" +
					"		.remove();\n" +
					"}\n" +
					"\n" +
					"sort_btn.on(\"click\", function(){\n" +
					"	var self = d3.select(this);\n" +
					"	var ascending = function(a,b){\n" +
					"		return a.value - b.value;\n" +
					"	};\n" +
					"	var descending = function(a,b){\n" +
					"		return b.value - a.value;\n" +
					"	}\n" +
					"	var state = +self.attr(\"state\");\n" +
					"	var txt = \"Sort data: \";\n" +
					"	if(state === 0){\n" +
					"		data.sort(ascending);\n" +
					"		state = 1;\n" +
					"		txt += \"descending\";\n" +
					"	} else if(state === 1){\n" +
					"		data.sort(descending);\n" +
					"		state = 0;\n" +
					"		txt += \"ascending\";\n" +
					"	}\n" +
					"	self.attr(\"state\", state);\n" +
					"	self.html(txt);\n" +
					"\n" +
					"	plot.call(chart, {\n" +
					"		data: data,\n" +
					"		axis:{\n" +
					"			x: xAxis,\n" +
					"			y: yAxis\n" +
					"		},\n" +
					"		gridlines: yGridlines,\n" +
					"		initialize: false\n" +
					"	});\n" +
					"});\n" +
					"\n" +
					"plot.call(chart, {\n" +
					"	data: data,\n" +
					"	axis:{\n" +
					"		x: xAxis,\n" +
					"		y: yAxis\n" +
					"	},\n" +
					"	gridlines: yGridlines,\n" +
					"	initialize: true\n" +
					"});\n" +
					"</script>");
			//out.println("<div>");
		}
	
	out.println("<script src='extra/jquery.js'></script><script src='extra/bootstrap.js'></script></body></html>");
	}

}

