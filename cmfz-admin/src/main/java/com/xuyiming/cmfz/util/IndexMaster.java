package com.xuyiming.cmfz.util;

import com.xuyiming.cmfz.entity.Master;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/19.
 */
public class IndexMaster {

    public static Map<String,Object> indexMaster(Integer nowPage,Integer pageSize,String keyword,String category) throws ParseException, IOException, InvalidTokenOffsetsException {
        System.out.println(nowPage + "---" + pageSize + "---" + keyword + "---" + category);

        String cate = category.substring(category.lastIndexOf("_")+1);

        System.out.println(cate);
        FSDirectory fsDirectory = FSDirectory.open(Paths.get("G:\\index\\02"));

        IndexReader indexReader = DirectoryReader.open(fsDirectory);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = new QueryParser(cate,new StandardAnalyzer());

        // 查所有
        Query query = queryParser.parse(keyword);

        TopDocs topDocs = null;

        if(nowPage <= 1){
            topDocs = indexSearcher.search(query,pageSize);
        }else{
            topDocs = indexSearcher.search(query,(nowPage-1)*pageSize); // zs ls
            // 当前页的第一条记录的上一条ScoreDoc对象
            ScoreDoc before = topDocs.scoreDocs[topDocs.scoreDocs.length-1];
            topDocs = indexSearcher.searchAfter(before, query, pageSize);
        }
        System.out.println("符合条件的记录条数："+topDocs.totalHits);

        // 高亮器
        Scorer scorer = new QueryScorer(query);
        org.apache.lucene.search.highlight.Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;font-size:16px'>","</span>");
        Highlighter highlighter = new Highlighter(formatter,scorer);

        // 分数文档对象 文档得分 + 文档在索引库中的编号
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        ArrayList<Master> masters = new ArrayList<Master>();

        for (ScoreDoc scoreDoc : scoreDocs) {
            //System.out.println("文档的得分："+scoreDoc.score); //lucene 分数自动相关度排序
            int docID = scoreDoc.doc;
           // System.out.println("文档在数据存储区中的编号："+docID);
            Document document = indexReader.document(docID);
            String content = highlighter.getBestFragment(new StandardAnalyzer(), cate, document.get(cate));

            Master master = new Master();
            master.setMasterId(Integer.parseInt(document.get("id")));
            master.setMasterName(document.get("name"));
            master.setMasterPhoto(document.get("phone"));
            master.setMasterrSummary(document.get("summary"));
            masters.add(master);
        }

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("rows",masters);
        map.put("total",topDocs.totalHits);

        return map;

    }

}
