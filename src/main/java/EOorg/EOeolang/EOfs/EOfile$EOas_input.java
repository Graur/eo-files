/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
// @checkstyle PackageNameCheck (1 line)
package EOorg.EOeolang.EOfs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.LinkedList;
import org.eolang.AtComposite;
import org.eolang.AtFree;
import org.eolang.Data;
import org.eolang.Dataized;
import org.eolang.PhDefault;
import org.eolang.Phi;

/**
 * File.as-input.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDollarSigns")
public class EOfile$EOas_input extends PhDefault {

    /**
     * Ctor.
     * @param sigma The \sigma
     * @checkstyle BracketsStructureCheck (200 lines)
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public EOfile$EOas_input(final Phi sigma) {
        this(sigma, null);
    }

    /**
     * Ctor.
     * @param sigma The \sigma
     * @param out Output stream of NULL
     * @checkstyle BracketsStructureCheck (200 lines)
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public EOfile$EOas_input(final Phi sigma, final InputStream out) {
        super(sigma);
        this.add("mode", new AtFree());
        this.add("buf", new AtFree());
        this.add("φ", new AtComposite(this, rho -> rho.attr("buf").get()));
        this.add("read", new AtComposite(this, self -> {
            InputStream stream = out;
            if (stream == null) {
                final String mode = new Dataized(
                    self.attr("mode").get()
                ).take(String.class);
                final Path path = Paths.get(
                    new Dataized(
                        self.attr("ρ").get()
                    ).take(String.class)
                );
                stream = EOfile$EOas_input.stream(path, mode);
            }
            return new EOfile$EOas_input$EOread(self, stream);
        }));
        this.add("close", new AtComposite(this, self -> {
            if (out != null) {
                out.close();
            }
            return new Data.ToPhi(true);
        }));
    }

    /**
     * Make an output stream.
     * @param path The path
     * @param opts Opts
     * @return Stream
     * @throws IOException If fails
     */
    private static InputStream stream(final Path path, final String opts)
        throws IOException {
        final Collection<OpenOption> options = new LinkedList<>();
        for (final char chr : opts.toCharArray()) {
            if (chr == 'r') {
                options.add(StandardOpenOption.READ);
            }
        }
        return Files.newInputStream(path, options.toArray(new OpenOption[0]));
    }

}
