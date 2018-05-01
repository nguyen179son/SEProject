/*
 * Copyright (C) 2014 jsonwebtoken.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

public class MacValidator implements SignatureValidator {

    private final MacSigner signer;

    public MacValidator(SignatureAlgorithm alg, Key key) {
        this.signer = new MacSigner(alg, key);
    }

    @Override
    public boolean isValid(byte[] data, byte[] signature) {
        byte[] computed = this.signer.sign(data);
        return MessageDigest.isEqual(computed, signature);
    }
}