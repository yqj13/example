<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书信息</title>
</head>

<link rel="stylesheet" href="element-ui/index.css">
<script src="./js/vue.js"></script>
<script src="./element-ui/index.js"></script>
<script src="./js/axios-0.18.0.js"></script>

<body>
    <h1 align="center">图书信息列表展示</h1>
    <div id="app">
        <el-input v-model="title" placeholder="输入图书标题"></el-input>
        <el-input v-model="searchAuthor" placeholder="输入图书作者"></el-input>
        <el-input v-model="searchStyle" placeholder="输入图书类型"></el-input>
        <el-button @click="searchBooks">搜索</el-button>
        <el-table :data="bookList" style="width: 100%"  stripe border >
            <el-table-column prop="bookName" label="图书名称" align="center" min-width="25%"></el-table-column>
            <el-table-column prop="author" label="作者" align="center" min-width="25%"></el-table-column>
            <el-table-column prop="bookStyle" label="图书类型" align="center"  min-width="25%"></el-table-column>
            <el-table-column prop="bookPrice" label="图书价格" align="center"  min-width="25%"></el-table-column>
        </el-table>
<!--       -->
        <el-pagination
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="pageSize"
                :total="totalBooks">
        </el-pagination>
    </div>
</body>

<style>
    .el-table .warning-row {
        background: oldlace;
    }
    .el-table .success-row {
        background: #f0f9eb;
    }
</style>

<script>
    new Vue({
        el: "#app",

        data() {
            return {
                searchTitle: ' ',
                searchAuthor: ' ',
                searchStyle:' ',
                bookList: [],
                currentPage:1,
                pageSize:10,
                totalBooks:0
            };
        },
        methods: {
            searchBooks(){
                axios.get('/api/books',{
                    bookName  : this.searchTitle,
                    author : this.searchAuthor,
                    bookStyle : this.searchStyle,
                    page:this.currentPage - 1,
                    size:this.pageSize
                })
                .then(response => {
                        this.bookList = response.data;
                    })
                        .catch(error => {
                            console.error(error);
                        });
                }
            },
        // searchBooks(){
        //     this.$axios.get('/api/books/search',{

        //     })
        //         .then(res => {
        //         if(res.data.code){
        //             this.bookList = res.data.content;
        //             this.totalBooks = res.data.totalElements;
        //         }
        //     })
        //         .catch((error) => {
        //             console.error(error);
        //         });
        // },
        handleCurrentChange(page) {
            this.currentPage = page;
            this.searchBooks();
        }


    });
</script>
</html>
