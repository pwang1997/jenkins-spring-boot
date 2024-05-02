package com.erp.erpspringboot.base.repositories;

import java.util.concurrent.CompletableFuture;

public interface AsyncSQLExecutor {

    CompletableFuture<?> doAction(String action);
}
