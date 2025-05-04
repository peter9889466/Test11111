package player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerService {
	private PlayerDAO playerDAO;
	private static final Logger logger = Logger.getLogger(PlayerService.class.getName());

	public PlayerService() {
		playerDAO = new PlayerDAO(); // DAO 객체 생성
	}

	public boolean registerPlayer(PlayerDTO player) {
		try {
			// 아이디 중복 체크
			if (isUsernameTaken(player.getUsername())) {
				return false; // 아이디가 이미 존재하면 실패
			}

			// 회원가입 등록
			playerDAO.registerPlayer(player);
			return true;
		} catch (Exception e) {
			// 예외 발생 시 로그 기록
			logger.log(Level.SEVERE, "회원가입 처리 중 오류 발생: " + e.getMessage(), e);
			return false;
		}
	}
	
	public ArrayList<PlayerDTO> Ranking() {
        return playerDAO.Ranking();
    }

	private boolean isUsernameTaken(String username) {
		return playerDAO.checkUsernameExists(username);
	}

	public PlayerDTO login(String username, String password) {
		if (password == null || password.isEmpty()) {
	        System.out.println("비밀번호가 입력되지 않았습니다.");
	        return null;  // 로그인 실패
	    }
		return playerDAO.login(username, password);
	}
	
	public boolean delete(String username, String password) {
		return playerDAO.delete(username, password);
	}
}