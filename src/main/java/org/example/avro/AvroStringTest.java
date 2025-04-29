package org.example.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AvroStringTest {

    @Test
    public  void TestStringSchemaWrite()  {
        // 定义一个 Avro schema
        String schemaString = "{\"type\": \"record\", \"name\": \"User\", \"fields\": [{\"name\": \"name\", \"type\": \"string\"}]}";
        Schema schema = new Schema.Parser().parse(schemaString);

        // 创建一个 GenericRecord 对象
        GenericRecord user = new GenericData.Record(schema);
        user.put("name", "Alice");

        File file = new File("output/users.avro");
        DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);

        try {

            dataFileWriter.create(schema, file);
            dataFileWriter.append(user);
            dataFileWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public  void TestStringSchemaRead()  {
        File file = new File("output/users.avro");
        try {
            DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(); // 使用GenericDatumReader处理通用数据模型
            DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader);
            GenericRecord record = null;
            while (dataFileReader.hasNext()) { // 循环读取每条记录
                record = dataFileReader.next(record); // 将读取的记录赋值给record变量（如果需要的话）
                System.out.println(record); // 打印记录内容
            }
            dataFileReader.close(); // 关闭文件读取器
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
