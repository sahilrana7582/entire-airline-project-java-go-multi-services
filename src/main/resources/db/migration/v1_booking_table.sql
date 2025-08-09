CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS booking (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    reference_code varchar(12) NOT NULL UNIQUE,
    customer_id uuid NOT NULL,
    flight_id uuid NOT NULL,
    status TEXT NOT NULL CHECK (status IN (
        'PENDING_PAYMENT',
        'CONFIRMED',
        'CANCELLED',
        'CHECKED_IN',
        'BOARDING',
        'COMPLETED'
    )),
    payment_id uuid,
    total_amount numeric(10,2) NOT NULL,
    ticket uuid,

    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz NOT NULL DEFAULT now()
);