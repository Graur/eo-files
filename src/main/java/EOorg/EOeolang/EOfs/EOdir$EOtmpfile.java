/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 Yegor Bugayenko
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eolang.AtLambda;
import org.eolang.Data;
import org.eolang.Dataized;
import org.eolang.PhDefault;
import org.eolang.PhWith;
import org.eolang.Phi;

/**
 * Dir.tmpfile.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDollarSigns")
public class EOdir$EOtmpfile extends PhDefault {

    /**
     * Ctor.
     * @param sigma The \sigma
     * @checkstyle BracketsStructureCheck (200 lines)
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public EOdir$EOtmpfile(final Phi sigma) {
        super(sigma);
        this.add("φ", new AtLambda(this, rho -> {
            final Path home = Paths.get(
                new Dataized(
                    rho.attr("ρ").get()
                ).take(String.class)
            );
            final Path path = Files.createTempFile(home, null, null);
            path.toFile().deleteOnExit();
            return new PhWith(
                new EOfile(rho), "path",
                new Data.ToPhi(path.toAbsolutePath().toString())
            );
        }));
    }

}
