public final class Contact {
    private String name;
    private String email;
    private String phoneNumber;

    public Contact(String name, String email, String phoneNumber) {
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setName(String name) {
        if (name != null && name.trim().matches("^[A-Za-z][A-Za-z '\\-]+$")) {
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException(
                "Name must be at least 2 characters (letters, spaces, ' and - only)");
        }
    }

    public void setEmail(String email) {
        if (email != null && email.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            this.email = email.trim();
        } else {
            throw new IllegalArgumentException("Invalid email (example: user@example.com)");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        String p = phoneNumber == null ? "" : phoneNumber.trim().replaceFirst("^\\+91", "");
        if (p.matches("^[6-9][0-9]{9}$")) {
            this.phoneNumber = p;
        } else {
            throw new IllegalArgumentException(
                "Phone must be 10 digits starting with 6-9 (optional +91 prefix)");
        }
    }
    @Override
    public String toString(){
        return String.format("%-20s%-30s%-15s", name, email, phoneNumber);
    }
}