

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
@WebServlet("/RegionWise")
public class RegionWise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegionWise() {
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
		Session session3 = cluster2.connect();
		String id = request.getParameter("options");
		String tabl = request.getParameter("type");
		out.println("<!DOCTYPE html><html><head><title>Region Wise Distribution</title><link rel='stylesheet' href='extra/main.css'><script  type='text/javascript' src='extra/d3.min.js'></script><link type= 'text/css' rel='stylesheet' href='extra/bootstrap.css'/><link type='text/css' rel='stylesheet' href='extra/divform.css' /><link rel='stylesheet' href='extra/font-awesome-4.3.0/css/font-awesome.min.css'></head><body><div class='navbar navbar-default navbar-fixed-top' role='navigation'><div class= 'container-fluid'><div class='navbar-header'><button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'><span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></button><a href='#' class='navbar-brand'>Project</a> </div><div class='navbar-collapse'><ul class='nav navbar-nav'><li><a href='#'></a> </li><li><a href='#'></a>  </li><li><a href='#'></a> </li><li><a href='/minor/BarChart'><i class='fa fa-bar-chart'></i> Bar chart</a></li><li><a href='minor/RegionWise'><i class='fa fa-th'></i> Region Wise Distribution</a></li><li><a href='#'><i class='fa fa-th-list'></i> Category Wise Distribution</a></li><li><a href='#'><i class='fa fa-magic'></i> Insightful Algorithms</a></li></ul></div></div></div><br><br><div id='formbar'><form class='form-horizontal' action='RegionWise'><div class='form-group'><label for='city' class='col-sm-3 control-label'><font face='verdana'>Please select Ad Id to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='options'><option id='null'>null</option><option id='1458'>1458</option><option id='3258'>3358</option><option id='3386'>3386</option><option id='3427'>3427</option><option id='3476'>3476</option></select></div><div class='form-group'><label for='city' class='col-sm-2 control-label'><font face='verdana'>Sector to Analyze</font></label><div class='col-sm-2'><select class='form-control' name='type'><option id='bid'>Bids</option><option id='impression'>Impressions</option><option id='clicks'>Clicks</option><option id='converts'>Converts</option></select></div><div class='col-sm-2'><button type='submit' class='btn btn-primary'>Analyze</button></div></div></form></div>");
		
		if(id!=null){
			out.println(id+"<br>");
			out.println(tabl+"<br>");
			String table = "";
			switch(tabl){
				case "Bids":
					table = "Bid_process";
					break ;
				case "Impressions":
					table = "imp_process";
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
				String queryString1 = "SELECT count (*) FROM ad_db."+table+" where advertiser_id='"+id+"' AND region='"+city_no[i]+"'";
				ResultSet result1 = session.execute(queryString1);
				Row row1 = result1.one();
				city_count[i] = row1.getLong("count");
				//out.println(city_count[i]+"</br>");
			}
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
					"				\"label\": \"unknown\",\n" +
					"				\"value\": "+city_count[0]+",\n" +
					"				\"color\": \"#2484c1\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"beijing\",\n" +
					"				\"value\": "+city_count[1]+",\n" +
					"				\"color\": \"#0c6197\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"tianjin\",\n" +
					"				\"value\": "+city_count[2]+",\n" +
					"				\"color\": \"#4daa4b\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"hebei\",\n" +
					"				\"value\": "+city_count[3]+",\n" +
					"				\"color\": \"#90c469\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"shanxi\",\n" +
					"				\"value\": "+city_count[4]+",\n" +
					"				\"color\": \"#daca61\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"neimenggu\",\n" +
					"				\"value\": "+city_count[5]+",\n" +
					"				\"color\": \"#e4a14b\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"liaoning\",\n" +
					"				\"value\": "+city_count[6]+",\n" +
					"				\"color\": \"#e98125\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"jilin\",\n" +
					"				\"value\": "+city_count[7]+",\n" +
					"				\"color\": \"#cb2121\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"heilongjiang\",\n" +
					"				\"value\": "+city_count[8]+",\n" +
					"				\"color\": \"#830909\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"shanghai\",\n" +
					"				\"value\": "+city_count[9]+",\n" +
					"				\"color\": \"#923e99\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"jiangsu\",\n" +
					"				\"value\": "+city_count[10]+",\n" +
					"				\"color\": \"#ae83d5\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"zhejiang\",\n" +
					"				\"value\": "+city_count[11]+",\n" +
					"				\"color\": \"#bf273e\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"anhui\",\n" +
					"				\"value\": "+city_count[12]+",\n" +
					"				\"color\": \"#ce2aeb\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"fujian\",\n" +
					"				\"value\": "+city_count[13]+",\n" +
					"				\"color\": \"#bca44a\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"jiangxi\",\n" +
					"				\"value\": "+city_count[14]+",\n" +
					"				\"color\": \"#618d1b\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"shandong\",\n" +
					"				\"value\": "+city_count[15]+",\n" +
					"				\"color\": \"#1ee67b\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"henan\",\n" +
					"				\"value\": "+city_count[16]+",\n" +
					"				\"color\": \"#b0ec44\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"hubei\",\n" +
					"				\"value\": "+city_count[17]+",\n" +
					"				\"color\": \"#a4a0c9\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"hunan\",\n" +
					"				\"value\": "+city_count[18]+",\n" +
					"				\"color\": \"#322849\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"guangdong\",\n" +
					"				\"value\": "+city_count[19]+",\n" +
					"				\"color\": \"#86f71a\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"guangxi\",\n" +
					"				\"value\": "+city_count[20]+",\n" +
					"				\"color\": \"#d1c87f\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"hainan\",\n" +
					"				\"value\": "+city_count[21]+",\n" +
					"				\"color\": \"#7d9058\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"chongqing\",\n" +
					"				\"value\": "+city_count[22]+",\n" +
					"				\"color\": \"\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"sichuan\",\n" +
					"				\"value\": "+city_count[23]+",\n" +
					"				\"color\": \"#7c37c0\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"guizhou\",\n" +
					"				\"value\": "+city_count[24]+",\n" +
					"				\"color\": \"#cc9fb1\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"yunnan\",\n" +
					"				\"value\": "+city_count[25]+",\n" +
					"				\"color\": \"#e65414\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"xizang\",\n" +
					"				\"value\": "+city_count[26]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"shannxi\",\n" +
					"				\"value\": "+city_count[27]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"gansu\",\n" +
					"				\"value\": "+city_count[28]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"qinghai\",\n" +
					"				\"value\": "+city_count[29]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"ningxia\",\n" +
					"				\"value\": "+city_count[30]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"xinjiang\",\n" +
					"				\"value\": "+city_count[31]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"taiwan\",\n" +
					"				\"value\": "+city_count[32]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"xianggang\",\n" +
					"				\"value\": "+city_count[33]+",\n" +
					"				\"color\": \"#8b6834\"\n" +
					"			},\n" +
					"			{\n" +
					"				\"label\": \"aomen\",\n" +
					"				\"value\": "+city_count[34]+",\n" +
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
			
			
		}
		
		out.println("<script src='extra/jquery.js'></script><script src='extra/bootstrap.js'></script></body></html>");
		
	}

}
