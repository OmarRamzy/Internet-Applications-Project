/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-12-12 8:52:15 PM                        */
/*==============================================================*/

use survey_db;


drop table if exists ADMIN;

drop table if exists CHOICE;

drop table if exists Message;

drop table if exists NOTIFICATION;

drop table if exists QUESTION;

drop table if exists QuestionAnswer;

drop table if exists Relationship10;

drop table if exists SURVEY;

drop table if exists USER;

drop table if exists UserSurveyAnswer;

/*==============================================================*/
/* Table: ADMIN                                                 */
/*==============================================================*/
create table ADMIN
(
   AdminUsername        varchar(100) not null,
   Password             varchar(100) not null,
   primary key (AdminUsername)
);

/*==============================================================*/
/* Table: CHOICE                                                */
/*==============================================================*/
create table CHOICE
(
   ChoiceID             int not null,
   QuestionID           int not null,
   ChoiceText           text not null,
   ChoiceNumber         int not null,
   primary key (ChoiceID)
);

/*==============================================================*/
/* Table: Message                                               */
/*==============================================================*/
create table Message
(
   MessageID            int not null,
   Username             varchar(100) not null,
   AdminUsername        varchar(100) not null,
   MessageText          text not null,
   SendTime             datetime not null,
   IsRead               bool not null,
   primary key (MessageID)
);

/*==============================================================*/
/* Table: NOTIFICATION                                          */
/*==============================================================*/
create table NOTIFICATION
(
   ReportID             int not null,
   Username             varchar(100) not null,
   SurveyID             int not null,
   ReportText           varchar(500) not null,
   CreatedTime          datetime not null,
   primary key (ReportID)
);

/*==============================================================*/
/* Table: QUESTION                                              */
/*==============================================================*/
create table QUESTION
(
   QuestionID           int not null,
   SurveyID             int not null,
   QuestionText         text not null,
   QuestionType         varchar(5) not null,
   QuestionNumber       int not null,
   IsMandatory          bool not null,
   primary key (QuestionID)
);

/*==============================================================*/
/* Table: QuestionAnswer                                        */
/*==============================================================*/
create table QuestionAnswer
(
   QuestionAnswerID     int not null,
   QuestionID           int not null,
   AnswerID             int,
   FreeAnswer           text,
   primary key (QuestionAnswerID)
);

/*==============================================================*/
/* Table: Relationship10                                        */
/*==============================================================*/
create table Relationship10
(
   QuestionAnswerChoiceID int not null,
   QuestionAnswerID     int not null,
   ChoiceID             int not null,
   primary key (QuestionAnswerChoiceID)
);

/*==============================================================*/
/* Table: SURVEY                                                */
/*==============================================================*/
create table SURVEY
(
   SurveyID             int not null,
   Username             varchar(100) not null,
   SurveyName           varchar(50) not null,
   SurveyDescription    text not null,
   CreatedDate          date not null,
   CloseTime            datetime not null,
   IsSuspended          bool not null,
   IsClosed             bool not null,
   primary key (SurveyID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   Username             varchar(100) not null,
   Password             varchar(100) not null,
   FirstName            varchar(50) not null,
   LastName             varchar(50),
   EmailAddress         varchar(800),
   IsSuspended          bool not null,
   primary key (Username)
);

/*==============================================================*/
/* Table: UserSurveyAnswer                                      */
/*==============================================================*/
create table UserSurveyAnswer
(
   AnswerID             int not null,
   Username             varchar(100) not null,
   SurveyID             int not null,
   HideUserIdentity     bool not null,
   primary key (AnswerID)
);

alter table CHOICE add constraint FK_QuestionChoice foreign key (QuestionID)
      references QUESTION (QuestionID) on delete restrict on update restrict;

alter table Message add constraint FK_MessageCreator foreign key (AdminUsername)
      references ADMIN (AdminUsername) on delete restrict on update restrict;

alter table Message add constraint FK_UserMessage foreign key (Username)
      references USER (Username) on delete restrict on update restrict;

alter table NOTIFICATION add constraint FK_ReportCreator foreign key (Username)
      references USER (Username) on delete restrict on update restrict;

alter table NOTIFICATION add constraint FK_SurveySurveyReport foreign key (SurveyID)
      references SURVEY (SurveyID) on delete restrict on update restrict;

alter table QUESTION add constraint FK_SurveyQuestion foreign key (SurveyID)
      references SURVEY (SurveyID) on delete restrict on update restrict;

alter table QuestionAnswer add constraint FK_QuestionQuestionAnswer foreign key (QuestionID)
      references QUESTION (QuestionID) on delete restrict on update restrict;

alter table QuestionAnswer add constraint FK_UserSurveyQuestionAnswer foreign key (AnswerID)
      references UserSurveyAnswer (AnswerID) on delete restrict on update restrict;

alter table Relationship10 add constraint FK_Relationship14 foreign key (QuestionAnswerID)
      references QuestionAnswer (QuestionAnswerID) on delete restrict on update restrict;

alter table Relationship10 add constraint FK_Relationship15 foreign key (ChoiceID)
      references CHOICE (ChoiceID) on delete restrict on update restrict;

alter table SURVEY add constraint FK_SurveyOwner foreign key (Username)
      references USER (Username) on delete restrict on update restrict;

alter table UserSurveyAnswer add constraint FK_AnwerOwner foreign key (Username)
      references USER (Username) on delete restrict on update restrict;

alter table UserSurveyAnswer add constraint FK_SurveyUserAnswer foreign key (SurveyID)
      references SURVEY (SurveyID) on delete restrict on update restrict;

