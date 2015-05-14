//AdFormat


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
@WebServlet("/AdFormat")
public class AdFormat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdFormat() {
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
		Cluster cluster2 = Cluster.builder().addContactPoint("localhost").build();
		Session session2 = cluster2.connect();
		Cluster cluster3 = Cluster.builder().addContactPoint("localhost").build();
		Session session3 = cluster3.connect();
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
				+ "<br><br><br><br><div id='formbar'><form class='form-horizontal' action='AdFormat'><div class='form-group'><label for='city' class='col-sm-3 control-label'><font face='verdana'>Please select Ad Id to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='options'><option id='null'>null</option><option id='1458'>1458</option><option id='3258'>3358</option><option id='3386'>3386</option><option id='3427'>3427</option><option id='3476'>3476</option></select></div><div class='form-group'><label for='city' class='col-sm-2 control-label'><font face='verdana'>Sector to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='type'><option id='bid'>Bids</option><option id='impression'>Impressions</option><option id='clicks'>Clicks</option><option id='converts'>Converts</option></select></div><div class='col-sm-2'><button type='submit' class='btn btn-primary'>Analyze</button></div></div></form></div>");
		
		if(id!=null){
			out.println(id+"<br>");
			out.println(tabl+"<br>");
			String table = "";
			switch(tabl){
				case "Bids":
					table = "bid_format";
					break ;
				case "Impressions":
					table = "imp_format";
					break ;
				case "Clicks":
					table = "click_format";
					break ;
				case "Converts":
					table = "conv_format";
					break ;	
			}
			//int [] city_no = {0,1,2,3,15,27,40,55,65,79,80,94,106,124,134,146,164,183,201,216,238,253,275,276,298,308,325,333,344,359,368,374,393,394,395};
			//String [] city_name = {"unknown","beijing","ianjin","hebei","shanxi","neimenggu","liaoning","jilin","heilongjiang","shanghai","jiangsu","zhejiang","anhui","fujian","jiangxi","shandong","henan","hubei","hunan","guangdong","guangxi","hainan","chongqing","sichuan","guizhou","yunnan","xizang","shannxi","gansu","qinghai","ningxia","xinjiang","taiwan","xianggang","aomen"};
			long [] city_count = new long[3];
			//for(int i=0;i<city_no.length;i++){
			if(tabl.equals("Bids")){
				String queryString1 = "SELECT count (*) FROM ad_db."+table+"1 where advertiser_id='"+id+"' AND ad_slot_format='0' LIMIT 5000000";
				ResultSet result1 = session.execute(queryString1);
				Row row1 = result1.one();
				city_count[0] = row1.getLong("count");
				String queryString2 = "SELECT count (*) FROM ad_db."+table+"1 where advertiser_id='"+id+"' AND ad_slot_format='1' LIMIT 5000000";
				result1 = session.execute(queryString2);
				row1 = result1.one();
				city_count[1] = row1.getLong("count");
				String queryString3 = "SELECT count (*) FROM ad_db."+table+"1 where advertiser_id='"+id+"' AND ad_slot_format='5' LIMIT 5000000";
				result1 = session.execute(queryString3);
				row1 = result1.one();
				city_count[2] = row1.getLong("count");
				
				queryString1 = "SELECT count (*) FROM ad_db."+table+"2 where advertiser_id='"+id+"' AND ad_slot_format='0' LIMIT 5000000";
				result1 = session2.execute(queryString1);
				row1 = result1.one();
				city_count[0] += row1.getLong("count");
				queryString2 = "SELECT count (*) FROM ad_db."+table+"2 where advertiser_id='"+id+"' AND ad_slot_format='1' LIMIT 5000000";
				result1 = session2.execute(queryString2);
				row1 = result1.one();
				city_count[1] += row1.getLong("count");
				queryString3 = "SELECT count (*) FROM ad_db."+table+"2 where advertiser_id='"+id+"' AND ad_slot_format='5' LIMIT 5000000";
				result1 = session2.execute(queryString3);
				row1 = result1.one();
				city_count[2] += row1.getLong("count");
				
				queryString1 = "SELECT count (*) FROM ad_db."+table+"3 where advertiser_id='"+id+"' AND ad_slot_format='0' LIMIT 5000000";
				result1 = session3.execute(queryString1);
				row1 = result1.one();
				city_count[0] += row1.getLong("count");
				queryString2 = "SELECT count (*) FROM ad_db."+table+"3 where advertiser_id='"+id+"' AND ad_slot_format='1' LIMIT 5000000";
				result1 = session3.execute(queryString2);
				row1 = result1.one();
				city_count[1] += row1.getLong("count");
				queryString3 = "SELECT count (*) FROM ad_db."+table+"3 where advertiser_id='"+id+"' AND ad_slot_format='5' LIMIT 5000000";
				result1 = session3.execute(queryString3);
				row1 = result1.one();
				city_count[2] += row1.getLong("count");
				
			}
			else{
			String queryString1 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='"+id+"' AND ad_slot_format='0' LIMIT 5000000";
			ResultSet result1 = session.execute(queryString1);
			Row row1 = result1.one();
			city_count[0] = row1.getLong("count");
			String queryString2 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='"+id+"' AND ad_slot_format='1' LIMIT 5000000";
			result1 = session.execute(queryString2);
			row1 = result1.one();
			city_count[1] = row1.getLong("count");
			String queryString3 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='"+id+"' AND ad_slot_format='5' LIMIT 5000000";
			result1 = session.execute(queryString3);
			row1 = result1.one();
			city_count[2] = row1.getLong("count");
			}
				//out.println(city_count[i]+"</br>");
			//}
			//*
			out.println("<script src=\"extra/d3.min.js\"></script>\n" +
					"<script src=\"extra/d3pie.min.js\"></script>\n" +
					"<div id=\"pieChart\"></div>\n" +
					"<div style=\"display: table; margin-right: auto; margin-left: auto;\">");
			out.println("<script>\n" +
					"var pie = new d3pie(\"pieChart\", {\n" +
					"	\"header\": {\n" +
					"		\"title\": {\n" +
					"			\"text\": \"Ad Analytics\",\n" +
					"			\"fontSize\": 24,\n" +
					"			\"font\": \"open sans\"\n" +
					"		},\n" +
					"		\"subtitle\": {\n" +
					"			\"text\": \"A full pie chart to show off Region Wise distribution.\",\n" +
					"			\"color\": \"#999999\",\n" +
					"			\"fontSize\": 12,\n" +
					"			\"font\": \"open sans\"\n" +
					"		},\n" +
					"		\"titleSubtitlePadding\": 9\n" +
					"	},\n" +
					"	\"footer\": {\n" +
					"		\"color\": \"#999999\",\n" +
					"		\"fontSize\": 10,\n" +
					"		\"font\": \"open sans\",\n" +
					"		\"location\": \"bottom-left\"\n" +
					"	},\n" +
					"	\"size\": {\n" +
					"		\"canvasWidth\": 1250\n" +
					"	},\n" +
					"	\"data\": {\n" +
					"		\"sortOrder\": \"value-desc\",\n" +
					"		\"content\": [\n" +
					"			{\n" +
					"				\"label\": \"Unknown\",\n" +
					"				\"value\": "+city_count[0]+",\n" +
					"				\"color\": \"#2484c1\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"Fixed\",\n" +
					"				\"value\": "+city_count[1]+",\n" +
					"				\"color\": \"#0c6197\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"Popup\",\n" +
					"				\"value\": "+city_count[2]+",\n" +
					"				\"color\": \"#248838\"\n" +
					"			}\n" +
					"		]\n" +
					"	},\n" +
					"	\"labels\": {\n" +
					"		\"outer\": {\n" +
					"			\"pieDistance\": 32\n" +
					"		},\n" +
					"		\"inner\": {\n" +
					"			\"hideWhenLessThanPercentage\": 3\n" +
					"		},\n" +
					"		\"mainLabel\": {\n" +
					"			\"fontSize\": 11\n" +
					"		},\n" +
					"		\"percentage\": {\n" +
					"			\"color\": \"#ffffff\",\n" +
					"			\"decimalPlaces\": 0\n" +
					"		},\n" +
					"		\"value\": {\n" +
					"			\"color\": \"#adadad\",\n" +
					"			\"fontSize\": 11\n" +
					"		},\n" +
					"		\"lines\": {\n" +
					"			\"enabled\": true\n" +
					"		}\n" +
					"	},\n" +
					"	\"effects\": {\n" +
					"		\"pullOutSegmentOnClick\": {\n" +
					"			\"effect\": \"linear\",\n" +
					"			\"speed\": 400,\n" +
					"			\"size\": 8\n" +
					"		}\n" +
					"	},\n" +
					"	\"misc\": {\n" +
					"		\"gradient\": {\n" +
					"			\"enabled\": true,\n" +
					"			\"percentage\": 100\n" +
					"		}\n" +
					"	}\n" +
					"});\n" +
					"</script>");
			//*/
			 
			
		}
		
		out.println("<script src='extra/jquery.js'></script><script src='extra/bootstrap.js'></script></body></html>");
		
	}

}
