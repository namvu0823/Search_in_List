package com.example.search_in_list

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tạo danh sách sinh viên mẫu
        studentList = listOf(
            Student("Nguyen Van A", "20180001"),
            Student("Le Thi B", "20210002"),
            Student("Tran Thi C", "20190003"),
            Student("Pham Van D", "20200004"),
            Student("Le Van E", "20180005"),
            Student("Do Thi F", "20210006"),
            Student("Bui Van G", "20220007"),
            Student("Hoang Thi H", "20170008"),
            Student("Pham Van I", "20210009"),
            Student("Nguyen Thi J", "20180010"),
            Student("Truong Van K", "20190011"),
            Student("Ngo Thi L", "20200012"),
            Student("Tran Van M", "20220013"),
            Student("Nguyen Thi N", "20210014"),
            Student("Le Van O", "20180015"),
            Student("Pham Thi P", "20200016"),
            Student("Do Van Q", "20220017"),
            Student("Tran Thi R", "20190018"),
            Student("Nguyen Van S", "20210019"),
            Student("Hoang Thi T", "20180020")
        )

        // Khởi tạo RecyclerView và Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Xử lý sự kiện tìm kiếm
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (!newText.isNullOrBlank() && newText.length > 2) {
                    studentList.filter {
                        it.name.contains(newText, ignoreCase = true) || it.mssv.contains(newText)
                    }
                } else {
                    studentList
                }
                studentAdapter.updateList(filteredList)
                return true
            }
        })
    }
}
