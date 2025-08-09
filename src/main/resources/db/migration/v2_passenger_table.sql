CREATE TABLE IF NOT EXISTS passenger (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    booking_id uuid NOT NULL REFERENCES booking(id) ON DELETE CASCADE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    gender TEXT CHECK (gender IN('MALE', 'FEMALE')),
    passport_number VARCHAR(50) NOT NULL,
    passport_expiry DATE NOT NULL,

    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz NOT NULL DEFAULT now()
);