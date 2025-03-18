package Util;

import Attendance.AttendanceFile;
import Attendance.AttendanceList;
import FileHandlers.*;
import Tutorial.TutorialClass;
import Tutorial.TutorialClassList;

import java.util.ArrayList;

public class DataManager {
    private static final String DIRECTORY_PATH = "./data";
    private static final String TUTORIAL_FILE_PATH = DIRECTORY_PATH + "/AllTutorials.csv";
    private static final String ATTENDANCE_FILE_PATH = DIRECTORY_PATH + "/AttendanceFile.csv";

    public TutorialClassList loadTutorials() {
        FileCreator.createFileIfNotExists(TUTORIAL_FILE_PATH, DIRECTORY_PATH);
        FileLoader<TutorialClassList> tutorialLoader = new TutorialClassListFileLoader();
        return tutorialLoader.loadFromFile(TUTORIAL_FILE_PATH);
    }
    public AttendanceFile loadAttendanceFiles() {
        FileCreator.createFileIfNotExists(ATTENDANCE_FILE_PATH, DIRECTORY_PATH);
        FileLoader<AttendanceFile> AttendanceFileLoader = new AttendanceFileFileLoader();
        return AttendanceFileLoader.loadFromFile(ATTENDANCE_FILE_PATH);
    }

    public AttendanceFile createDemoAttendanceFile(TutorialClassList tutorialList, int numberOfWeeks) {
        ArrayList<AttendanceList> attendanceLists = new ArrayList<>();
        int tutorialLimit = Math.max(2, tutorialList.getTutorialClasses().size());

        for (int i = 0; i < tutorialLimit; i++) {
            TutorialClass tutorial = tutorialList.getTutorialClasses().get(i);
            for (int week = 1; week <= numberOfWeeks; week++) {
                AttendanceList attendanceList = new AttendanceList(tutorial, week);
                if (!tutorial.getStudentList().getStudents().isEmpty()) {
                    attendanceList.markAbsent(tutorial.getStudentList().getStudents().get(0));
                }
                attendanceLists.add(attendanceList);
            }
        }
        return new AttendanceFile(attendanceLists);
    }

    public void saveTutorials(TutorialClassList tutorialList) {
        FileSaver<TutorialClassList> tutorialSaver = new TutorialClassListFileSaver();
        tutorialSaver.saveToFile(tutorialList, DIRECTORY_PATH);
    }

    public void saveAttendanceFile(AttendanceFile attendanceFile) {
        FileSaver<AttendanceFile> attendanceSaver = new AttendanceFileFileSaver();
        attendanceSaver.saveToFile(attendanceFile, DIRECTORY_PATH);
    }

    public String getTutorialFilePath() {
        return TUTORIAL_FILE_PATH;
    }

    public String getAttendanceFilePath() {
        return ATTENDANCE_FILE_PATH;
    }
}