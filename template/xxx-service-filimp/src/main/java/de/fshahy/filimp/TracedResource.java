
package de.fshahy.filimp;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * A simple JAX-RS resource used for tracing
 */
@Path("/tracing")
public class TracedResource {

    private Span span;

    private Tracer tracer;

    @Inject
    TracedResource(Span span, Tracer tracer) {
        this.span = span;
        this.tracer = tracer;
    }

    /**
     * Return a worldly greeting message.
     *
     * @return {@link String}
     */
    @GET
    @WithSpan("default")
    public String getDefaultMessage() {
        return "Hello World!";
    }

    /**
     * Create an internal custom span and return its description.
     *
     * @return custom span
     */
    @GET
    @Path("custom")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSpan
    public String useCustomSpan() {
        Span span = tracer.spanBuilder("custom")
                .setSpanKind(SpanKind.INTERNAL)
                .setAttribute("attribute", "value")
                .startSpan();
        span.end();

        return "Custom Span " + span;
    }

    /**
     * Get Span info.
     *
     * @return {@link GreetingMessage}
     */
    @GET
    @Path("span")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSpan
    public String getSpanInfo() {
        return "Span " + span.toString();
    }

}
