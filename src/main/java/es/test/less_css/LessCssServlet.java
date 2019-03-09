package es.test.less_css;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;

import com.inet.lib.less.Less;

import lombok.Getter;
import lombok.Setter;

/**
 * A on the fly css generator from less file.
 */
@WebServlet("/style.css")
public class LessCssServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final boolean useCache;
	@Getter @Setter
	private static @Nullable String cachedCss = null;
	
	public LessCssServlet(@Value("${app.lesscss.useCache}") boolean useCache) {
		this.useCache = useCache;
	}

	@Override
	protected void doGet(@Nullable HttpServletRequest req, @Nullable HttpServletResponse response) throws IOException {
		if (response == null || req == null) {
			return;
		}
		
		String css;
		String lcachedCss = getCachedCss();
		if (!useCache || lcachedCss == null) {
			try (InputStream lessResource = this.getClass().getResourceAsStream("/static/style.less")) {
				String lessString = IOUtils.toString(lessResource, Charset.defaultCharset());
				css = Less.compile(null, lessString, true);
				setCachedCss(css);
			}
		} else {
			css = lcachedCss;
		}
		
		
        response.setContentType("text/css");
        try (PrintWriter out = response.getWriter()) {
        	out.println(css);
        }
	}

}
