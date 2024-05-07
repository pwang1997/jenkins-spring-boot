INSERT INTO permissions (id, name, resource, action, comment)
VALUES (1, 'CanReadStorage', 'storage', 'read-only', 'user have read-only access to storage'),
       (2, 'CanEditStorage', 'storage', 'edit', 'user have full access to storage'),
       (3, 'CanReadInvoice', 'invoice', 'read-only', 'user have read-only access to invoice'),
       (4, 'CanEditInvoice', 'invoice', 'edit', 'user have full access to invoice');
