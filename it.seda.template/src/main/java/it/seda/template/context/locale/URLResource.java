/**
 * 
 */
package it.seda.template.context.locale;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

/**
 * @author f.ricci
 *
 */
public class URLResource extends PostfixedResource {

    /** the URL where the contents can be found. */
    private URL url;
    /** if the URL matches a file, this is the file. */
    private File file;

    /**
     * Creates a URLApplicationResource for the specified path that can be accessed through the specified URL.
     *
     * @param localePath the path including localization.
     * @param url the URL where the contents can be found.
     */
    public URLResource(String localePath, URL url) {
        super(localePath);
        this.url = url;
        if ("file".equals(url.getProtocol())) {
            file = new File(url.getPath());
        }
    }

    /**
     * Creates a URLApplicationResource for the specified path that can be accessed through the specified URL.
     *
     * @param path the path excluding localization.
     * @param locale the Locale.
     * @param url the URL where the contents can be found.
     */
    public URLResource(String path, Locale locale, URL url) throws MalformedURLException {
        super(path, locale);
        this.url = url;
        if ("file".equals(url.getProtocol())) {
            file = new File(url.getPath());
        }
    }

    /** {@inheritDoc} */
    @Override
    public InputStream getInputStream() throws IOException {
        if (file != null) {
            return new FileInputStream(file);
        } else {
            return url.openConnection().getInputStream();
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public Reader getReader() throws IOException {
    	return new InputStreamReader(getInputStream());
    }    

    /** {@inheritDoc} */
    @Override
    public long getLastModified() throws IOException {
        if (file != null) {
            return file.lastModified();
        } else {
            URLConnection connection = url.openConnection();
            if (connection instanceof JarURLConnection) {
                return ((JarURLConnection) connection).getJarEntry().getTime();
            } else {
                long result = connection.getLastModified();
                return result;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Resource " + getLocalePath() + " at " + url.toString();
    }

    protected URL getURL(){
        return url;
    }

    protected File getFile(){
        return file;
    }
}
