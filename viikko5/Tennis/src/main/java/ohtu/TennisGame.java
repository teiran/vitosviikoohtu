package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (m_score1 == m_score2) {
            if (0 <= m_score1 && 3 >= m_score1) {
                score = cases(m_score1) + "-All";
            } else {
                score = "Deuce";
            }
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            score = cases2(m_score1 - m_score2);
            if (m_score1 > m_score2) {
                score += "player1";
            } else {
                score += "player2";
            }
        } else {
            score += cases(m_score1) + "-" + cases(m_score2);
        }
        return score;
    }

    private String cases2(int minusResult) {
        if (minusResult == 1 || minusResult == -1) {
            return "Advantage ";
        }
        return "Win for ";
    }

    private String cases(int tempScore) {
        String score = "";
        switch (tempScore) {
            case 0:
                score += "Love";
                break;
            case 1:
                score += "Fifteen";
                break;
            case 2:
                score += "Thirty";
                break;
            case 3:
                score += "Forty";
                break;
        }
        return score;
    }
}
