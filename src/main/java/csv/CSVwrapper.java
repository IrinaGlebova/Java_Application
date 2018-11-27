package csv;
import java.lang.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import exceptions.IncorrectDataException;


public class CSVwrapper {

    private Integer index;
    private String ssoid;
    private LocalDateTime ts;
    private String grp;
    private String type;
    private String subtype;
    private String  url;
    private String orgid;
    private String formid;
    private String code;
    private String ltpa;
    private String sudirresponse;
    private LocalDateTime ymdh;

    private static String dateTimeFormat = "yyyy-MM-dd-HH";

    public CSVwrapper() {
    }

    public CSVwrapper(Integer index, String ssoid, String tsString, String grp, String type, String subtype, String  url, String orgid, String formid, String code,String ltpa,
                      String sudirresponse, String ymdhString) throws IncorrectDataException{
        this.index = index;
        this.ssoid = ssoid;
        this.ts = CSVwrapper.parseTimeFromUnix(tsString);
        this.grp = grp;
        this.type = type;
        this.subtype = subtype;
        this.url = url;
        this.orgid = orgid;
        this.formid = formid;
        this.code = code;
        this.ltpa = ltpa;
        this.sudirresponse = sudirresponse;
        this.ymdh = CSVwrapper.parseDateFromString(ymdhString);
    }


    // parsing String to Integer
    public static Integer parseIntegerFromString (String str) throws IncorrectDataException{
        Integer value = null;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            System.out.println("CSVwrapper: cannot parse the string into integer because of: " + ex.getCause());
            throw new IncorrectDataException(ex.getMessage());
        }
        return value;
    }

    /*public void setTsFromString(String tsString) throws IncorrectDataException{
        this.ts = CSVwrapper.parseIntegerFromString(tsString);
    }*/
    public static LocalDateTime parseTimeFromUnix (String str) throws IncorrectDataException {
        LocalDateTime value = null;
        if (str == null) {
            throw new IncorrectDataException("Time string in Unix format is null");
        } else {
            try {
                Long unixTime  = Long.parseLong(str);
                value= LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTime),
                                TimeZone.getDefault().toZoneId());
            } catch (NumberFormatException ex) {
                System.out.println("CSVwrapper: cannot parse the string of Unix Time format into long: " + ex.getCause());
                throw new IncorrectDataException(ex.getMessage());
            }
        }
        return value;
    }

    public static LocalDateTime parseDateFromString(String line) throws IncorrectDataException {
        LocalDateTime value = null;
        if (line == null) {
            throw new IncorrectDataException("Time string is null");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CSVwrapper.dateTimeFormat);
                value = LocalDateTime.parse(line, formatter);
            } catch (java.time.format.DateTimeParseException ex) {
                System.out.println("CSVwrapper: DateTimeParseException while parsing String \"" + line + "\" to Date");
                try {
                    String cvsSplitBy = "-";
                    String[] arg = line.split(cvsSplitBy);
                    Integer year = Integer.getInteger(arg[0]);
                    Integer month = Integer.getInteger(arg[1]);
                    Integer day = Integer.getInteger(arg[2]);
                    Integer hour = Integer.getInteger(arg[3]);
                    System.out.println("The parsed string is year: " + year.toString() +
                            " month: " + month.toString() + " day: " + day.toString() + " hour: " + hour.toString());
                    value = LocalDateTime.of(year, month, day, hour, 0);
                } catch (Exception anyException) {
                    throw new IncorrectDataException(anyException.getMessage());
                }
            }
        }
        return value;
    }

    // the DateTimeFormatter object is bidirectional; it can both parse input and format output.
    public String outputDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CSVwrapper.dateTimeFormat);
        return formatter.format(this.ymdh);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    // autogenerated getters and setters methods
    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public LocalDateTime getTs() {
        return ts;
    }



    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLtpa() {
        return ltpa;
    }

    public void setLtpa(String ltpa) {
        this.ltpa = ltpa;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public void setSudirresponse(String sudirresponse) {
        this.sudirresponse = sudirresponse;
    }

    public LocalDateTime getYmdh() {
        return ymdh;
    }

    public void setYmdh(LocalDateTime ymdh) {
        this.ymdh = ymdh;
    }
}
