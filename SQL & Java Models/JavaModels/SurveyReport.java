/***********************************************************************
 * Module:  SurveyReport.java
 * Author:  abdal
 * Purpose: Defines the Class SurveyReport
 ***********************************************************************/

import java.util.*;

/** @pdOid 7a4a6d8e-e8f5-4b58-8780-e65400337dfa */
public class SurveyReport {
   /** @pdOid 0ac0c3c2-95ec-4c73-b3ef-a5dcdba37e11 */
   public int reportID;
   /** @pdOid 667ade63-66c4-4005-a36f-714d08dae174 */
   public java.lang.String reportText;
   /** @pdOid 395ce605-a202-43a9-a128-562be451bac6 */
   public java.util.Date createdTime;
   
   /** @pdRoleInfo migr=no name=Survey assc=surveySurveyReport mult=1..1 side=A */
   public Survey survey;
   /** @pdRoleInfo migr=no name=User assc=reportCreator mult=1..1 side=A */
   public User user;
   
   
   /** @pdGenerated default parent getter */
   public Survey getSurvey() {
      return survey;
   }
   
   /** @pdGenerated default parent setter
     * @param newSurvey */
   public void setSurvey(Survey newSurvey) {
      if (this.survey == null || !this.survey.equals(newSurvey))
      {
         if (this.survey != null)
         {
            Survey oldSurvey = this.survey;
            this.survey = null;
            oldSurvey.removeSurveyReport(this);
         }
         if (newSurvey != null)
         {
            this.survey = newSurvey;
            this.survey.addSurveyReport(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public User getUser() {
      return user;
   }
   
   /** @pdGenerated default parent setter
     * @param newUser */
   public void setUser(User newUser) {
      if (this.user == null || !this.user.equals(newUser))
      {
         if (this.user != null)
         {
            User oldUser = this.user;
            this.user = null;
            oldUser.removeSurveyReport(this);
         }
         if (newUser != null)
         {
            this.user = newUser;
            this.user.addSurveyReport(this);
         }
      }
   }

}