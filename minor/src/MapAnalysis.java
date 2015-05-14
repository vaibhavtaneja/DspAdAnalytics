

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
@WebServlet("/MapAnalysis")
public class MapAnalysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapAnalysis() {
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
		String id = request.getParameter("options");
		String tabl = request.getParameter("type");
		out.println("<!DOCTYPE html><html><head><title>Area Wise Analysis</title><link rel='stylesheet' href='extra/main.css'><script  type='text/javascript' src='extra/d3.min.js'></script><link type= 'text/css' rel='stylesheet' href='extra/bootstrap.css'/><link type='text/css' rel='stylesheet' href='extra/divform.css' /><link rel='stylesheet' href='extra/font-awesome-4.3.0/css/font-awesome.min.css'></head><body><div class='navbar navbar-default navbar-fixed-top' role='navigation'><div class= 'container-fluid'><div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></button><a href='#' class='navbar-brand'>Project</a> </div><div class='navbar-collapse'><ul class='nav navbar-nav'><li><a href='#'></a> </li><li><a href='#'></a>  </li><li><a href='#'></a> </li>"
				+ "<li><a href='/minor/BarChart'><i class='fa fa-bar-chart'></i> Advertiser Analysis</a></li>"
				+ "<li><a href='/minor/RegionWise'><i class='fa fa-pie-chart'></i> Area Wise Analysis</a></li>"
				+ "<li><a href='#'><i class='fa fa-th-list'></i> Categorical Distribution</a></li>"
				+ "<li><a href='#'><i class='fa fa-magic'></i> Insightful Algorithms</a></li></ul></div></div></div><br>"
				+ "<div style='margin-top:51px' class='navbar navbar-default navbar-fixed-top' role='navigation'><div class= 'container-fluid'><div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></button><a href='#' class='navbar-brand'>         </a> </div><div class='navbar-collapse'><ul style='margin-left:-2px' class='nav navbar-nav'><li><a href='#'></a><li><a href='#'></a><li><a href='#'></a> </li><li><a href='#'></a>  </li><li><a href='#'></a> </li>"
				+ "<li><a href='/minor/MapAnalysis'><i class='fa fa-map-marker'></i> Map  DSPs  Analysis      </a></li>"
				+ "<li><a href='/minor/RegionPerformance'><i class='fa fa-users'></i> Region's Standings</a></li>"
				+ "<li><a href='#'><i class='fa fa-th-list'></i> Category Wise Distribution</a></li>"
				+ "<li><a href='#'><i class='fa fa-magic'></i> Insightful Algorithms</a></li></ul></div></div></div>"
				+ "<br><br><br><br><div id='formbar'><form class='form-horizontal' action='MapAnalysis'><div class='form-group'><label for='city' class='col-sm-3 control-label'><font face='verdana'>Please select Ad Id to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='options'><option id='null'>null</option><option id='1458'>1458</option><option id='3258'>3358</option><option id='3386'>3386</option><option id='3427'>3427</option><option id='3476'>3476</option></select></div><div class='form-group'><label for='city' class='col-sm-2 control-label'><font face='verdana'>Sector to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='type'><option id='bid'>Bids</option><option id='impression'>Impressions</option><option id='clicks'>Clicks</option><option id='converts'>Converts</option></select></div><div class='col-sm-2'><button type='submit' class='btn btn-primary'>Analyze</button></div></div></form></div>"
				+"<label for='mapcity' id='san'></label><p style='text-align:center'><div id='mapholder'></div></p>");
		
		if(id!=null){
			out.println(id+"<br>");
			out.println(tabl+"<br>");
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
			String [] city_name = {"unknown","beijing","ianjin","hebei","shanxi","neimenggu","liaoning","jilin","heilongjiang","shanghai","jiangsu","zhejiang","anhui","fujian","jiangxi","shandong","henan","hubei","hunan","guangdong","guangxi","hainan","chongqing","sichuan","guizhou","yunnan","xizang","shannxi","gansu","qinghai","ningxia","xinjiang","taiwan","xianggang","aomen"};
			long [] city_count = new long[35];
			for(int i=0;i<city_no.length;i++){
				String queryString1 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='"+id+"' AND region='"+city_no[i]+"'LIMIT 800000";
				ResultSet result1 = session.execute(queryString1);
				Row row1 = result1.one();
				city_count[i] = row1.getLong("count");
				//out.println(city_count[i]+"</br>");
			}
			
			out.println("<script src=\"http://maps.google.com/maps/api/js?sensor=false\"></script>        \n" +
			          "    <script>\n" +
			          "\n" +
			         
			          "    if (navigator.geolocation) {\n" +
			          "        navigator.geolocation.getCurrentPosition(showPosition, showError);\n" +
			          "    } else { \n" +
			          "        x.innerHTML = \"Geolocation is not supported by this browser.\";\n" +
			          "    }\n" +
			          
			          "\n" +
			          "function showPosition(position) {\n" +
			          "    \n" +
			          "    \n" +
			          "var x = document.getElementById(\"san\").value;\n" +
			          "    var lat = [\"39.9167\", \"39.1333\", \"39.3000\",\"37.7000\",\"44.0000\",\"41.1000\",\"43.7000\",\"48.0000\",\"31.2000\",\"32.9000\",\"29.2000\",\"31.8333\",\"25.9000\",\"27.3000\",\"36.4000\",\"33.9000\",\"31.2000\",\"27.4000\",\"23.4000\",\"23.6000\",\"19.2000\",\"29.5583\",\"30.1333\",\"26.8333\",\"25.0500\",\"31.7056\",\"35.6000\",\"38.0000\",\"35.0000\",\"36.6000\",\"41.0000\",\"23.5000\",\"23.3924\",\"28.0333\"];                                                                                                                                 \n" +
			          " var lon = [\"116.3833\", \"117.1833\", \"116.7000\",\"112.4000\",\"113.0000\",\"122.3000\",\"126.2000\",\"129.0000\",\"121.5000\",\"119.8000\",\"120.5000\",\"117.0000\",\"118.3000\",\"116.0000\",\"118.4000\",\"113.5000\",\"112.3000\",\"111.8000\",\"113.5000\",\"108.3000\",\"109.7000\",\"106.5667\",\"102.9333\",\"106.8333\",\"101.8667\",\"86.9403\",\"108.4000\",\"102.0000\",\"96.0000\",\"105.3200\",\"85.0000\",\"121.0000\",\"113.2990\",\"110.405\"]; \n" +
			          "     \n" +
			          "  \n" +
			          "    latlon34 = new google.maps.LatLng(35.0000, 103.0000 )\n" +
			          "    latlon0 = new google.maps.LatLng(lat[0], lon[0])\n" +
			          "    latlon1 = new google.maps.LatLng(lat[1], lon[1])\n" +
			          "    latlon2 = new google.maps.LatLng(lat[2], lon[2])\n" +
			          "    latlon3 = new google.maps.LatLng(lat[3], lon[3])\n" +
			          "    latlon4 = new google.maps.LatLng(lat[4], lon[4])\n" +
			          "    latlon5 = new google.maps.LatLng(lat[5], lon[5])\n" +
			          "    latlon6 = new google.maps.LatLng(lat[6], lon[6])\n" +
			          "    latlon7 = new google.maps.LatLng(lat[7], lon[7])\n" +
			          "    latlon8 = new google.maps.LatLng(lat[8], lon[8])\n" +
			          "    latlon9 = new google.maps.LatLng(lat[9], lon[9])\n" +
			          "    latlon10 = new google.maps.LatLng(lat[10], lon[10])\n" +
			          "    latlon11 = new google.maps.LatLng(lat[11], lon[11])\n" +
			          "    latlon12 = new google.maps.LatLng(lat[12], lon[12])\n" +
			          "    latlon13 = new google.maps.LatLng(lat[13], lon[13])\n" +
			          "    latlon14 = new google.maps.LatLng(lat[14], lon[14])\n" +
			          "    latlon15 = new google.maps.LatLng(lat[15], lon[15])\n" +
			          "    latlon16 = new google.maps.LatLng(lat[16], lon[16])\n" +
			          "    latlon17 = new google.maps.LatLng(lat[17], lon[17])\n" +
			          "    latlon18 = new google.maps.LatLng(lat[18], lon[18])\n" +
			          "    latlon19 = new google.maps.LatLng(lat[19], lon[19])\n" +
			          "    latlon20 = new google.maps.LatLng(lat[20], lon[20])\n" +
			          "    latlon21 = new google.maps.LatLng(lat[21], lon[21])\n" +
			          "    latlon22 = new google.maps.LatLng(lat[22], lon[22])\n" +
			          "    latlon23 = new google.maps.LatLng(lat[23], lon[23])\n" +
			          "    latlon24 = new google.maps.LatLng(lat[24], lon[24])\n" +
			          "    latlon25 = new google.maps.LatLng(lat[25], lon[25])\n" +
			          "    latlon26 = new google.maps.LatLng(lat[26], lon[26])\n" +
			          "    latlon27 = new google.maps.LatLng(lat[27], lon[27])\n" +
			          "    latlon28 = new google.maps.LatLng(lat[28], lon[28])\n" +
			          "    latlon29 = new google.maps.LatLng(lat[29], lon[29])\n" +
			          "    latlon30 = new google.maps.LatLng(lat[30], lon[30])\n" +
			          "    latlon31 = new google.maps.LatLng(lat[31], lon[31])\n" +
			          "    latlon32 = new google.maps.LatLng(lat[32], lon[32])\n" +
			          "    latlon33 = new google.maps.LatLng(lat[33], lon[33])\n" +
			          "       \n" +
			          "    var area =[\"beijing\",\"tianjin\",\"hebei\",\"shanxi\",\"neimenggu\",\"liaoning\",\"jilin\",\"heilongjiang\",\"shanghai\",\"jiangsu\",\"zhejiang\",\"anhui\",\"fujian\",\"jiangxi\",\"shandong\",\"henan\",\"hubei\",\"hunan\",\"guangdong\",\"guangxi\",\"hainan\",\"chongqing\",\"sichuan\",\"guizhou\",\"yunnan\",\"xizang\",\"shannxi\",\"gansu\",\"qinghai\",\"ningxia\",\"xinjiang\",\"taiwan\",\"xianggang\",\"aomen\",\"unknown\"]; \n" +
			          "    \n" +
			          "    mapholder = document.getElementById('mapholder')\n" +
			          "    mapholder.style.height = '370px';\n" +
			          "    mapholder.style.width = '1300px';\n" +
			          "\n" +
			          "   var myOptions = {\n" +
			          "    center:latlon34,zoom:4,\n" +
			          "    mapTypeId:google.maps.MapTypeId.ROADMAP,\n" +
			          "    mapTypeControl:false,\n" +
			          "    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}\n" +
			          "    }\n" +
			          "     var map = new google.maps.Map(document.getElementById(\"mapholder\"), myOptions);\n" +
			          "    \n" +
			          "    \n" +
			          "  var marker0 = new google.maps.Marker({position:latlon0,map:map,title:area[0] +\"  \"+\""+city_count[1]+"\"});\n" +
			          "        var marker1 = new google.maps.Marker({position:latlon1,map:map,title:area[1] +\"  \"+\""+city_count[2]+"\"});\n" +
			          "        var marker2 = new google.maps.Marker({position:latlon2,map:map,title:area[2] +\"  \"+\""+city_count[3]+"\"});\n" +
			          "        var marker3 = new google.maps.Marker({position:latlon3,map:map,title:area[3] +\"  \"+\""+city_count[4]+"\"});\n" +
			          "        var marker4 = new google.maps.Marker({position:latlon4,map:map,title:area[4] +\"  \"+\""+city_count[5]+"\"});\n" +
			          "        var marker5 = new google.maps.Marker({position:latlon5,map:map,title:area[5] +\"  \"+\""+city_count[6]+"\"});\n" +
			          "        var marker6 = new google.maps.Marker({position:latlon6,map:map,title:area[6] +\"  \"+\""+city_count[7]+"\"});\n" +
			          "        var marker7 = new google.maps.Marker({position:latlon7,map:map,title:area[7] +\"  \"+\""+city_count[8]+"\"});\n" +
			          "        var marker8 = new google.maps.Marker({position:latlon8,map:map,title:area[8] +\"  \"+\""+city_count[9]+"\"});\n" +
			          "        var marker9 = new google.maps.Marker({position:latlon9,map:map,title:area[9] +\"  \"+\""+city_count[10]+"\"});\n" +
			          "        var marker10 = new google.maps.Marker({position:latlon10,map:map,title:area[10] +\"  \"+\""+city_count[11]+"\"});\n" +
			          "        var marker11= new google.maps.Marker({position:latlon11,map:map,title:area[11] +\"  \"+\""+city_count[12]+"\"});\n" +
			          "        var marker12 = new google.maps.Marker({position:latlon12,map:map,title:area[12] +\"  \"+\""+city_count[13]+"\"});\n" +
			          "        var marker13 = new google.maps.Marker({position:latlon13,map:map,title:area[13] +\"  \"+\""+city_count[14]+"\"});\n" +
			          "        var marker14 = new google.maps.Marker({position:latlon14,map:map,title:area[14] +\"  \"+\""+city_count[15]+"\"});\n" +
			          "        var marker15 = new google.maps.Marker({position:latlon15,map:map,title:area[15] +\"  \"+\""+city_count[16]+"\"});\n" +
			          "        var marker16 = new google.maps.Marker({position:latlon16,map:map,title:area[16] +\"  \"+\""+city_count[17]+"\"});\n" +
			          "        var marker17 = new google.maps.Marker({position:latlon17,map:map,title:area[17] +\"  \"+\""+city_count[18]+"\"});\n" +
			          "        var marker18 = new google.maps.Marker({position:latlon18,map:map,title:area[18] +\"  \"+\""+city_count[19]+"\"});\n" +
			          "        var marker19 = new google.maps.Marker({position:latlon19,map:map,title:area[19] +\"  \"+\""+city_count[20]+"\"});\n" +
			          "        var marker20 = new google.maps.Marker({position:latlon20,map:map,title:area[20] +\"  \"+\""+city_count[21]+"\"});\n" +
			          "        var marker21 = new google.maps.Marker({position:latlon21,map:map,title:area[21] +\"  \"+\""+city_count[22]+"\"});\n" +
			          "        var marker22 = new google.maps.Marker({position:latlon22,map:map,title:area[22] +\"  \"+\""+city_count[23]+"\"});\n" +
			          "        var marker23 = new google.maps.Marker({position:latlon23,map:map,title:area[23] +\"  \"+\""+city_count[24]+"\"});\n" +
			          "        var marker24 = new google.maps.Marker({position:latlon24,map:map,title:area[24] +\"  \"+\""+city_count[25]+"\"});\n" +
			          "        var marker25 = new google.maps.Marker({position:latlon25,map:map,title:area[25] +\"  \"+\""+city_count[26]+"\"});\n" +
			          "        var marker26 = new google.maps.Marker({position:latlon26,map:map,title:area[26] +\"  \"+\""+city_count[27]+"\"});\n" +
			          "        var marker27 = new google.maps.Marker({position:latlon27,map:map,title:area[27] +\"  \"+\""+city_count[28]+"\"});\n" +
			          "        var marker28 = new google.maps.Marker({position:latlon28,map:map,title:area[28] +\"  \"+\""+city_count[29]+"\"});\n" +
			          "        var marker29 = new google.maps.Marker({position:latlon29,map:map,title:area[29] +\"  \"+\""+city_count[30]+"\"});\n" +
			          "        var marker30 = new google.maps.Marker({position:latlon30,map:map,title:area[30] +\"  \"+\""+city_count[31]+"\"});\n" +
			          "        var marker31 = new google.maps.Marker({position:latlon31,map:map,title:area[31] +\"  \"+\""+city_count[32]+"\"});\n" +
			          "        var marker32 = new google.maps.Marker({position:latlon32,map:map,title:area[32] +\"  \"+\""+city_count[33]+"\"});\n" +
			          "        var marker33 = new google.maps.Marker({position:latlon33,map:map,title:area[33] +\"  \"+\""+city_count[34]+"\"});\n" +
			          "   \n" +
			          "    \n" +
			          "    }\n" +
			          "\n" +
			          "function showError(error) {\n" +
			          "    switch(error.code) {\n" +
			          "        case error.PERMISSION_DENIED:\n" +
			          "            x.innerHTML = \"User denied the request for Geolocation.\"\n" +
			          "            break;\n" +
			          "        case error.POSITION_UNAVAILABLE:\n" +
			          "            x.innerHTML = \"Location information is unavailable.\"\n" +
			          "            break;\n" +
			          "        case error.TIMEOUT:\n" +
			          "            x.innerHTML = \"The request to get user location timed out.\"\n" +
			          "            break;\n" +
			          "        case error.UNKNOWN_ERROR:\n" +
			          "            x.innerHTML = \"An unknown error occurred.\"\n" +
			          "            break;\n" +
			          "    }\n" +
			          "}\n" +
			          "</script>");
		}
		
		out.println("<script src='extra/jquery.js'></script><script src='extra/bootstrap.js'></script></body></html>");
		
	}

}
