package com.n.in.client;

import java.net.http.HttpRequest;

public interface RequestStrategy {
    HttpRequest build(Object param);
}
