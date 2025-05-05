package pixelpulse.eclesiasticasbackend.dto.requests;



public class LoginResponseDTO {
    private String idToken;
    private String refreshToken;
    private String expiresIn;
    private String localId;
    private String email;
    private boolean registered;
    private String error;
    
    public LoginResponseDTO() {
    	
    };
    
	public LoginResponseDTO(String idToken, String refreshToken, String expiresIn, String localId, String email,
			boolean registered, String error) {
		super();
		this.idToken = idToken;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
		this.localId = localId;
		this.email = email;
		this.registered = registered;
		this.error = error;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
    
    
}
