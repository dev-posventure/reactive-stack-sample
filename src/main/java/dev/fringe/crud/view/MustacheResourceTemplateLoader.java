package dev.fringe.crud.view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.TemplateLoader;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Mustache TemplateLoader implementation that uses a prefix, suffix and the Spring
 * Resource abstraction to load a template from a file, classpath, URL etc. A
 * [TemplateLoader] is needed in the [Compiler] when you want to render
 * partials (i.e. tiles-like features).
 *
 * @author Dave Syer
 * @see Mustache
 * @see Resource
 * @since 1.2.2
 */
public class MustacheResourceTemplateLoader implements TemplateLoader, ResourceLoaderAware {

    private String suffix = "classpath:templates/";
    private String prefix = ".html";
    private String charset = "UTF-8";

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    public MustacheResourceTemplateLoader(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * Set the charset.
     *
     * @param charset the charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * Set the resource loader.
     *
     * @param resourceLoader the resource loader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Reader getTemplate(String name) throws IOException {
        return new InputStreamReader(this.resourceLoader
                .getResource(this.prefix + name + this.suffix).getInputStream(),
                this.charset);
    }

}