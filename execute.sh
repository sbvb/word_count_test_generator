# java -jar word_count_test_generator.jar output_folder max_nummber max_per_file
# java -Xmx30g -jar dist/word_count_test_generator.jar /home/sbvb/zzz/ 1000 100

# pedido do Sedat,  45 tabelas de 100000 palavras, com os mesmos 3000
java -Xmx30g -jar dist/word_count_test_generator.jar /home/sbvb/zzz/ 3000 10000

